一、rabbitMQ简介
1.1、rabbitMQ的优点（适用范围）
1. 基于erlang语言开发具有高可用高并发的优点，适合集群服务器。
2. 健壮、稳定、易用、跨平台、支持多种语言、文档齐全。
3. 有消息确认机制和持久化机制，可靠性高。
4. 开源
其他MQ的优势：
1. Apache ActiveMQ曝光率最高，但是可能会丢消息。
2. ZeroMQ延迟很低、支持灵活拓扑，但是不支持消息持久化和崩溃恢复。
1.2、几个概念说明
producer&Consumer
producer指的是消息生产者，consumer消息的消费者。
Queue
消息队列，提供了FIFO的处理机制，具有缓存消息的能力。rabbitmq中，队列消息可以设置为持久化，临时或者自动删除。
设置为持久化的队列，queue中的消息会在server本地硬盘存储一份，防止系统crash，数据丢失
设置为临时队列，queue中的数据在系统重启之后就会丢失
设置为自动删除的队列，当不存在用户连接到server，队列中的数据会被自动删除Exchange
Exchange类似于数据通信网络中的交换机，提供消息路由策略。rabbitmq中，producer不是通过信道直接将消息发送给queue，而是先发送给Exchange。一个Exchange可以和多个Queue进行绑定，producer在传递消息的时候，会传递一个ROUTING_KEY，Exchange会根据这个ROUTING_KEY按照特定的路由算法，将消息路由给指定的queue。和Queue一样，Exchange也可设置为持久化，临时或者自动删除。
Exchange有4种类型：direct(默认)，fanout, topic, 和headers，不同类型的Exchange转发消息的策略有所区别：
Direct
直接交换器，工作方式类似于单播，Exchange会将消息发送完全匹配ROUTING_KEY的Queue
fanout
广播是式交换器，不管消息的ROUTING_KEY设置为什么，Exchange都会将消息转发给所有绑定的Queue。
topic
主题交换器，工作方式类似于组播，Exchange会将消息转发和ROUTING_KEY匹配模式相同的所有队列，比如，ROUTING_KEY为user.stock的Message会转发给绑定匹配模式为 * .stock,user.stock， * . * 和#.user.stock.#的队列。（ * 表是匹配一个任意词组，#表示匹配0个或多个词组）
headers
消息体的header匹配（ignore）
Binding
所谓绑定就是将一个特定的 Exchange 和一个特定的 Queue 绑定起来。Exchange 和Queue的绑定可以是多对多的关系。
virtual host
在rabbitmq server上可以创建多个虚拟的message broker，又叫做virtual hosts (vhosts)。每一个vhost本质上是一个mini-rabbitmq server，分别管理各自的exchange，和bindings。vhost相当于物理的server，可以为不同app提供边界隔离，使得应用安全的运行在不同的vhost实例上，相互之间不会干扰。producer和consumer连接rabbit server需要指定一个vhost。
1.3、消息队列的使用过程
1. 客户端连接到消息队列服务器，打开一个channel。
2. 客户端声明一个exchange，并设置相关属性。
3. 客户端声明一个queue，并设置相关属性。
4. 客户端使用routing key，在exchange和queue之间建立好绑定关系。
5. 客户端投递消息到exchange。
6. exchange接收到消息后，就根据消息的key和已经设置的binding，进行消息路由，将消息投递到一个或多个队列里