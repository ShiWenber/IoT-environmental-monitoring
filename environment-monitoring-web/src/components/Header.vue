<!--Header.vue-->
<template>
    <div class="header">
        <div class="left">
            <span style="font-size: 20px">{{ state.name }}</span>
        </div>
        <div class="right">
            <el-popover placement="bottom" :width="320" trigger="click" popper-class="popper-user-box">
                <template #reference>
                    <div class="author">
                        <i class="icon el-icon-s-custom" />
                        {{ state.userInfo && state.userInfo.username|| '' }}
                        <i class="el-icon-caret-bottom" />
                    </div>
                </template>
                <div class="nickname">
                    <p>登录名：{{ state.userInfo && state.userInfo.username|| '' }}</p>
                    <!-- <p>昵称：{{ state.userInfo && state.userInfo.name|| '' }}</p> -->
                    <el-tag size="small" effect="dark" class="logout" @click="logout">退出</el-tag>
                </div>
            </el-popover>
        </div>
    </div>
</template>

<script setup lang="ts">
// export default {
//     name: 'Header'
// }
import { reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router'
// 获取路由实例
const router = useRouter()
// 声明路由和 title 对应的键值对
const pathMap = {
    index: '首页',
    add: '添加数据记录'
}
const state = reactive({
    name: '首页'
})
// 监听路由变化方法 afterEach
router.afterEach((to) => {
    console.log('to', to)
    // to 能获取到路由相关信息。
    const { id } = to.query
    state.name = pathMap[to.name]
    state.userInfo = JSON.parse(localStorage.getItem('userInfo') || '{}')
    console.log('state.userInfo', state.userInfo)
})
</script>

<style scoped>
.header {
    height: 50px;
    border-bottom: 1px solid #e9e9e9;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
}

.right>div>.icon {
    font-size: 18px;
    margin-right: 6px;
}

.author {
    margin-left: 10px;
    cursor: pointer;
}
</style>

<style>
 .popper-user-box {
    background: url('https://s.yezgea02.com/lingling-h5/static/account-banner-bg.png') 50% 50% no-repeat!important;
    background-size: cover!important;
    border-radius: 0!important;
  }
   .popper-user-box .nickname {
    position: relative;
    color: #ffffff;
  }
  .popper-user-box .nickname .logout {
    position: absolute;
    right: 0;
    top: 0;
    cursor: pointer;
  }
</style>