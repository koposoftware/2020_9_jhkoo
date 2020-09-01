/**
 * login, join 시 미입력을 방지하기위한 alert fucntion
 * @param obj
 * @param msg
 * @returns
 */

function isNull(obj, msg){
	if(obj.value == ''){
		alert(msg)
		obj.focus()
		return true
	}
	return false
}