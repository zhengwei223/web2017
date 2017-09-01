package org.lanqiao.showcase.search;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class LuceneDemo {
  private final String INDEX_PATH = "e:\\lucene_demo_index";
  private Directory dir = null;


  /**
   * 获取IndexWriter实例
   * @return
   * @throws Exception
   */
  private IndexWriter getWriter() throws Exception {
    /**
     * 生成的索引我放在了e盘，可以根据自己的需要放在具体位置
     */
    Path indexPath = Paths.get( INDEX_PATH );
    File indexDir = indexPath.toFile();
    if (indexDir.exists() == false) {
      indexDir.mkdirs();
    }
    dir = FSDirectory.open( indexPath );
    SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
    IndexWriterConfig iwc = new IndexWriterConfig( analyzer );
    IndexWriter writer = new IndexWriter( dir, iwc );
    return writer;
  }

  /**
   * 添加数据
   * @param
   */
  public void addIndex(Resource resource) throws Exception {
    IndexWriter writer = getWriter();
    Document doc = new Document();
    doc.add( new StringField( "path", resource.getFile().getAbsolutePath(), Field.Store.YES ) );
    /**
     * yes是会将数据存进索引，如果查询结果中需要将记录显示出来就要存进去，如果查询结果
     * 只是显示标题之类的就可以不用存，而且内容过长不建议存进去
     * 使用TextField类是可以用于查询的。
     * 这里只存储路径，但内容用于检索。
     */
    doc.add( new TextField( "content", getContent( resource ), Field.Store.NO ) );
    writer.addDocument( doc );
    writer.close();
  }

  private String getContent(Resource docPath) throws IOException {
    return IOUtils.toString( new InputStreamReader( docPath.getInputStream() ) );
  }

  /**
   * 更新索引
   * @param resource
   * @throws Exception
   */
  public void updateIndex(Resource resource)throws Exception{
    IndexWriter writer=getWriter();
    Document doc=new Document();
    String absolutePath = resource.getFile().getAbsolutePath();
    doc.add( new StringField( "path", absolutePath, Field.Store.YES ) );
    doc.add( new TextField( "content", getContent( resource ), Field.Store.NO ) );

    Term pathTerm = new Term( "path", absolutePath );
    writer.updateDocument( pathTerm, doc);
    writer.close();
  }
  /**
   * 删除指定的索引
   * @param resource
   * @throws Exception
   */
  public void deleteIndex(Resource resource)throws Exception{
    IndexWriter writer=getWriter();
    String absolutePath = resource.getFile().getAbsolutePath();
    writer.deleteDocuments(new Term("path", absolutePath));
    writer.forceMergeDeletes(); // 强制删除
    writer.commit();
    writer.close();
  }
  /**
   * 根据关键字查询
   * @param q 查询关键字
   * @return
   * @throws Exception
   */
  public List<String> searchByContent(String q) throws Exception {
    /**
     * 注意的是查询索引的位置得是存放索引的位置，不然会找不到。
     */
    dir = FSDirectory.open( Paths.get( INDEX_PATH ) );
    IndexReader reader = DirectoryReader.open( dir );
    IndexSearcher indexSearcher = new IndexSearcher( reader );
    SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
    /**
     * content就是我们需要进行查找的字段
     * 同时在存放索引的时候要使用TextField类进行存放。
     */
    QueryParser parser = new QueryParser( "content", analyzer );
    Query query = parser.parse( q );

    TopDocs hits = indexSearcher.search( query, 100 ); // 查询命中
    List<String> userList = new LinkedList<String>();
    for (ScoreDoc scoreDoc : hits.scoreDocs) {
      Document doc = indexSearcher.doc( scoreDoc.doc );
      userList.add( doc.get( "path" ) ); // 存了什么，就可以取什么
    }
    return userList;
  }

  @Test
  public void testBuildIndex() throws Exception {
    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    Resource[] resources = resourcePatternResolver.getResources( "classpath*:docs/*.html" );
    for (int i = 0; i < resources.length; i++) {
      addIndex( resources[i] );
    }
  }

  @Test
  public void testSearch() throws Exception {
    List<String> files = searchByContent( "spring" );
    for (String f : files) {
      System.out.println( f );
    }
  }
}


