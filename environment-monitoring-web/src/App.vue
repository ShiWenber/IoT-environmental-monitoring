<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import { reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router'

// 不需要菜单的路径数组
const noMenu = ['/login']
const router = useRouter()
const state = reactive({
  showMenu: true, // 是否需要显示菜单
})
// 监听路由的变化
router.beforeEach((to) => {
  state.showMenu = !noMenu.includes(to.path)
})
</script>

<template>
  <div class="layout">
    <el-container v-if="state.showMenu" class="container">
      <el-aside class="aside">
        <!--系统名称+logo-->
        <div class="head">
          <div>
            <!-- <img src="./assets/logo.svg" alt="logo"> -->
            <span>环境监控</span>
          </div>
        </div>
        <!--一条为了美观的线条-->
        <div class="line" />
        <el-menu background-color="#222832" text-color="#fff" router="true">
          <!--一级栏目-->
          <el-sub-menu index="1">
            <template #title>
              <span>Dashboard</span>
            </template>
            <!--二级栏目-->
            <el-menu-item-group>
              <el-menu-item index="/"><el-icon>
                  <DataLine />
                </el-icon>首页</el-menu-item>
              <el-menu-item index="/login"><el-icon>
                  <DataLine />
                </el-icon>登录</el-menu-item>
              <el-menu-item index="/data"><el-icon>
                  <User />
                </el-icon>数据管理</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container class="content">
        <Header />
        <div class="main">
          <!--将 <router-view></router-view> 移到这里，并且用单标签-->
          <router-view />
        </div>
        <Footer />
      </el-container>
    </el-container>
    <el-container v-else class="container">
      <router-view />
    </el-container>
  </div>
</template>

<style scoped>
.layout {
  min-height: 100vh;
  background-color: #ffffff;
}

.container {
  height: 100vh;
}

.aside {
  width: 200px !important;
  background-color: #222832;
}

.head {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
}

.head>div {
  display: flex;
  align-items: center;
}

.head img {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}

.head span {
  font-size: 20px;
  color: #ffffff;
}

.line {
  border-top: 1px solid hsla(0, 0%, 100%, .05);
  border-bottom: 1px solid rgba(0, 0, 0, .2);
}

.content {
  display: flex;
  flex-direction: column;
  max-height: 100vh;
  overflow: hidden;
}

.main {
  height: calc(100vh - 100px);
  overflow: auto;
  padding: 10px;
}
</style>

<style>
body {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
</style>