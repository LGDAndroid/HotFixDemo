# HotFixDemo
热修复（阿里的Mobile Hotfix）傻瓜式配置，简单，热更新


* 接入准备：

 ```
 1. https://www.aliyun.com 有阿里云账号，并且已经实名验证过了。  
 ```
 ```
 2. 点击顶部标题栏"控制台"，在阿里云 "产品与服务" 中搜索"热修复" , 点击进入EMAS 产品列表，你需要做的就是点击"添加产品"，然后点击"添加应用" 
 ```
 ```
 3. 在刚刚新建的应用中 下载"aliyun-emas-services.json"，因为这个文件中有三个参数需要android Demo中配置的。（重点）
 ```
 ```
 4. 打开第3步中的"aliyun-emas-services.json"，里面的参数 hotfix.idSecret （IDSECRET），httpdns.secretKey （APPSECRET），hotfix.rsaSecret（RSASECRET），
    上面最容易出错的是很多人把IDSECRET填写错误，最新版本的阿里云应用配置是appkey，而这里需要填写appId。（重点）
 ```
 ```
 5. 配置安卓工程: 
        <1> build.gradle 中 allprojects 中 配置 maven { url "http://maven.aliyun.com/nexus/content/repositories/releases" }
        <2> build.gradle 中 dependencies 中 implementation 'com.aliyun.ams:alicloud-android-hotfix:3.2.3' （这里可以看阿里云热修复中最新的版本）
        <3> AndroidManifest.xml 中 配置第4步中的三个参数 meta-data 。
 ```
 ```
 6. 签名打包，签名文件填写的时候一定要勾选V1跟V2否则在6.0以下的手机安装失败，切记。
 ```
 ```
 7. 下载补丁：https://help.aliyun.com/document_detail/53247.html 阿里补丁下载，这个补丁下载之后，如果你是Mac环境，一定要安装在application应用目录下面，
    这个问题很容易弄错，否则打补丁的时候一直报资源找不到的错误。
 ```
 ```
 8. 填写补丁各种配置，根据自己的需求来勾选。最后点击Go，生成补丁，这里说明一下，新旧补丁一定要不一样，别弄成了一个文件。
 ```
 ```
 9. 在阿里云管理控制台中的"移动热修复"中的"补丁管理"，中添加补丁，这里创建的版本最好跟android工程中的versionName一样，然后上传补丁。
 ```   
 ```
 10. 点击补丁详情进入发布界面，点击新建发布（灰度发布跟全量发布），选择好发布，android工程才能接受到补丁并且更新，并且不需要重启，立即生效。
 ```

      

      

      

      

      