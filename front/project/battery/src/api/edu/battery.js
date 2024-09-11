import request from '@/utils/request'

export default {
getBatteryListPage(current,limit,batteryQuery) {
    return request({
        url: `/batteryservice/battery-info/pageBatteryCondition/${current}/${limit}`,
        method: 'post',
        //teacherQuery条件对象，后端使用RequestBody获取数据
        //data表示把对象转换json进行传递到接口里面
        data: batteryQuery
      })
    },
}