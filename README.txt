1、下载git客户端并安装
2、GitHub网站创建一个仓库
3、git clone 地址，把这个仓库下载到本地，把远程仓库克隆到本地














git status 查看本地没有提交的代码，
git config --global user.email "897617479@qq.com"
git log 查看提交日志信息

git add . 添加要提交的文件
git commit -m "修改了那些内容进行说明"
git push 提交到远程服务器

更新代码流程
git status （查看本地分支文件信息，确保更新时不产生冲突）
git branch （查看当前分支情况）
git checkout remote branch （若分支为本地分支，则需要切换到服务器的远程分支）
git pull 更新本地代码














































Android 告警逻辑梳理
 
告警级别梳理：
	实时告警，紧急告警，重要告警，一般告警
	当选中实时告警时，下拉箭头出现，再次点击实时告警时弹出告警级别选择框
	当选中历史告警时，下拉箭头消失，然后切换到实时告警时下拉箭头出现
	
	告警级别点击后，alarmFragment.requestAlarmData("紧急告警"),回调到AlarmFragment，
	1、弹出加载中的Dialog
	2、把当前的状态修改成刷新数据而不是加载更多
	3、交给realTimeAlarm 从第0页开始请求数据，
	4、请求成功后，取消掉Dialog，然后修改当前告警级别信息
	重新切换导航栏之后，清除所有的过滤信息
	------------------------------------------------------------------------------------
	按照机房、设备类型等信息进行筛选
	1、只有在实时告警页面才能过滤这些告警信息，在历史告警页面不能进行条件筛选
	2、alarmFilterDialog 这个dialog是复用的，只创建一次
	3、过滤条件类型，这个是使用自定义控件完成
	4、过滤条件设备，首先是展示所有的设备
	5、然后是当选中某个类型时，先清空之前选中的类型。然后设置当前选中类型的背景，最后把类型过滤条件保存起来
	6、当选中某个类型时，先清空之前选中类型对应的设备信息，然后移除掉所有的设备，最后根据选中的类型添加对应的设备
	
	点击重置，除了告警级别不清空之外，剩下的所有筛选条件都清空
	点击完成，先把当前的Dialog取消掉，然后走的流程和告警级别流程是一样的alarmFragment.requestAlarmData("")
	
	
	
1、android:windowBackground不影响Activity的生命周期,即启动设置了该属性的Activity的生命周期不会受到影响。
2、android:windowIsTranslucent = true会影响到Activity的生命周期，比如启动一个设置了该属性的Activity，
若该activity对用户可见时，启动它的activity（即它下层的activity）并未完全不可见（即调用到了onPause，不会调用onStop方法），具体可见上面的操作对Activity生命周期的影响。


Android 代码混淆
资源文件混淆：1 保护，2 压缩（不损失质量的情况下，让图片进行压缩）apk的瘦身



问题记录：
	textView通过代码设置为 粗体 
	TextPaint paint2 = textView2.getPaint();
    paint2.setFakeBoldText(true);//设置粗体显示
	
	
	如果父控件设置了    setScaleX(0.5) 和 setScaleY(0.5)  setScaleX 和 setScaleY 的缩放是根据自身view的中心点进行缩放，
	父控件设置setScaleX 和 setScaleY 那么对应的子view也进行了缩放



















