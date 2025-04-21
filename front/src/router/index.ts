import { createRouter, createWebHistory } from 'vue-router'
import Index from '@/views/Index.vue'
import NotFound from "@/views/NotFound.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/home',
    },
    {
      path: '/layout',
      name: 'layout',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: Index,
      children: [
        {
          path: '/home',
          name: 'home',
          component: () => import('@/views/home/Index.vue')
        },
        {
          path: '/login',
          name: 'login',
          component: () => import("@/views/login/Index.vue")
        },
        {
          path: '/register',
          name: 'register',
          component: () => import("@/views/register/Index.vue")
        },
        {
          path: '/profile/:userId',
          name: 'profile',
          component: () => import("@/views/profile/Index.vue")
        },
        {
          path: '/settings',
          component: () => import("@/views/settings/Index.vue")
        },
        {
          path: '/article',
          children: [
            {
              path: '',
              redirect: '/404'
            },
            {
              path: ':id',
              component: () => import("@/views/article/ArticleDetail.vue")
            },
            {
              path: 'create',
              component: () => import("@/views/article/ArticleForm.vue")
            },
            {
              path: 'edit/:id',
              component: () => import("@/views/article/ArticleForm.vue")
            }
          ]
        },

      ]
    },
    {
      path: '/404',
      name: '404',
      component: NotFound
    }
  ],
})

export default router
