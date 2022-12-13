import { createRouter, createWebHistory } from 'vue-router'
import Index from '@/views/IndexView.vue'
import AddRecord from '@/views/AddRecordView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/add',
      name: 'add',
      component: AddRecord
    },
    {
      path: '/data',
      name: 'data',
      component: () => import('../views/DataView.vue')
    }
  ]
})

export default router
