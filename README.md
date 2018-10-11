# springboot_template
oss-api定义接口的方法
oss-boot项目 只有springboot启动的类跟配置文件
oss-client 远程调用其他项目
oss-core springboot核心代码 如controller跟service都放在这里
oss-generator 接口涉及到的数据库相关的对象都放在这里

oss-boot引用oss-core
oss-core引用oss-api,oss-generator,oss-client