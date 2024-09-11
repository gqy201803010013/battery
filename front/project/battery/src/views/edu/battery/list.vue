<template>
  <div class="app-container">

        <!--查询表单-->
        <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
            <el-input v-model="batteryQuery.entry_person_name" placeholder="录入人姓名"/>
        </el-form-item>

        <el-form-item>
            <el-select v-model="batteryQuery.operation_status" clearable placeholder="运营状态">
            <el-option :value="1" label="在线"/>
            <el-option :value="0" label="离线"/>
            </el-select>
        </el-form-item>
        
        <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
        </el-form>


        <!-- 分页查询 -->
        <el-table
          v-loading="listLoading"
          :data="list"
          element-loading-text="数据加载中"
          border
          fit
          highlight-current-row>
          <el-table-column
          label="序号"
          width="70"
          align="center">

          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>

        </el-table-column>

          <el-table-column prop="batteryCode" label="电池编码" width="80" />

          <el-table-column label="运行状态" width="80">
            <template slot-scope="scope">
              {{ scope.row.operationStatus===0?'离线':'在线' }}
            </template>
          </el-table-column>
          remainingPower
          <el-table-column prop="remainingPower" label="剩余电量" />
          <el-table-column prop="entryPersonName" label="录入人姓名" />
          <el-table-column prop="supplierName" label="供应商" />
          

        </el-table>

        <!-- 分页 -->
        <el-pagination
            :current-page="page"
            :page-size="limit"
            :total="total"
            style="padding: 30px 0; text-align: center;"
            layout="total, prev, pager, next, jumper"
            @current-change="getList"
        />
    </div>
</template>

<script>
import battery from '@/api/edu/battery'
export default {


  data() { //定义变量和初始值
        return {
          list:null,//查询之后接口返回集合
          page:1,//当前页
          limit:10,//每页记录数
          total:0,//总记录数
          batteryQuery:{} //条件封装对象
        }
    },
    created() { //页面渲染之前执行，一般调用methods定义的方法
        //调用
        this.getList() 
        // this.removeDataById()
    },
    methods:{  //创建具体的方法，调用teacher.js定义的方法
        //讲师列表的方法
        getList(page=1) {
            this.page = page
            battery.getBatteryListPage(this.page,this.limit,this.batteryQuery)
                .then(response =>{//请求成功
                    //response接口返回的数据
                    this.list = response.data.rows
                    this.total = response.data.total
                    console.log(response)
                    console.log(this.list)
                }) 
        },
        resetData(){
          //清除查询
            this.batteryQuery = {}
            //查询所有
            this.getList()
        },
        
    
  }
}
</script>