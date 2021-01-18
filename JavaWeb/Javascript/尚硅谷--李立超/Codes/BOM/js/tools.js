// 定义移动
/**
 * @param {Object} obj 要执行动画的对象
 * @param {Object} attr 样式参数
 * @param {Object} speed 移动速度 
 * @param {Object} target 目标方向 
 * @param {Object} callback 回调函数
 */

function move(obj, attr, speed, target, callback){
	// 调用定时器之前关闭其他定时器
	clearInterval(obj.timer);
	
	// 通过getStyle获取样式值
	var oldValue = parseInt(getStyle(obj, attr));
	
	if(oldValue > target){
		
		speed = -speed;
	}
	
	var newValue = oldValue;
	// 在执行动画对象中添加属性
	obj.timer = setInterval(function(){
		newValue += speed;
		obj.style[attr] = newValue + "px";
		if(speed > 0 && newValue > target || speed < 0 && newValue <= target){
			
			clearInterval(obj.timer);
			callback && callback();
		} 
	}, 30)
}

/**
 * 定义函数获取当前指定元素的样式
 * 参数：
 * 	obj 要获取样式元素
 * 	name 要获取的样式
 */
function getStyle(obj, name){
	
	// 区分ie8和其他浏览器
	/**
	 * getCumputedStyle是变量，如果不存在则报错
	 * window.getCumputedStyle是属性，如果不存在则返回undefined
	 */
	if(window.getComputedStyle){
		return window.getComputedStyle(obj, null)[name];
	}else{
		return obj.currentStyle[name];
	}
}