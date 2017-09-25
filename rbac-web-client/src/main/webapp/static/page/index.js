function load(url) {
    layui.use('jquery', function ($) {
        $(".layui-body").load(url);
    })
}

layui.use(['element', 'laytpl', 'jquery'], function (element, laytpl, $) {
    var sideData = [{
        title: "菜单一",
        list: [{title: "菜单11", url: sys.projBaseUrl + "/static/page/user/list.html"}, {
            title: "菜单12",
            url: sys.projBaseUrl + "/static/page/user/list.html"
        }]
    }, {
        title: "菜单二",
        list: [{title: "菜单21", url: sys.projBaseUrl + "/static/page/user/list.html"}, {
            title: "菜单22",
            url: sys.projBaseUrl + "/static/page/user/list.html"
        }]
    }];
    $("#header").load(sys.projBaseUrl + "/static/page/layout/header.html", function () {

        laytpl($("#header").html()).render({}, function (htmlFlag) {
            $(".layui-header").html(htmlFlag);
        })
    })
    $("#side").load(sys.projBaseUrl + "/static/page/layout/side.html", function () {

        laytpl($("#side").html()).render(sideData, function (htmlFlag) {
            $(".layui-side").html(htmlFlag);
        })
    })
    $("#footer").load(sys.projBaseUrl + "/static/page/layout/footer.html", function () {

        laytpl($("#footer").html()).render({}, function (htmlFlag) {
            $(".layui-footer").html(htmlFlag);
        })
    })

});