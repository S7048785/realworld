import { createRouter, createWebHistory } from 'vue-router'
import Index from '@/views/Index.vue'
import NotFound from "@/views/NotFound.vue";
import {loginValidToast} from "@/utils/toast.ts";

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
          path: '/profile/:username',
          name: 'profile',
          component: () => import("@/views/profile/Index.vue")
        },
        {
          path: '/settings',
          name: 'settings',
          component: () => import("@/views/settings/Index.vue"),
          meta: {
            requiredAuth: true,
          }
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
              name: 'create',
              component: () => import("@/views/article/ArticleForm.vue"),
              meta: {
                requiredAuth: true,
              }
            },
            {
              path: 'edit/:id',
              name: 'edit',
              component: () => import("@/views/article/ArticleForm.vue"),
              meta: {
                requiredAuth: true,
              }
            }
          ]
        },

      ]
    },
    {
      path: '/404',
      name: '404',
      component: NotFound
    },
    // 捕获所有未定义路由，重定向到404
    // {
    //   path: '/:pathMatch(.*)*',  // 或 path: '/:catchAll(.*)'
    //   redirect: '/404'
    // }
  ],
})

const path1 = [
  '/register', '/login'
]

const path2 = [
  '/settings'
]

router.beforeEach((to, from, next) => {
  // console.log(to.name === 'profil')
  // if (to.name === 'profile') {
  //   if (/\D+/.test(to.params.userId as string)) {
  //     next('/404')
  //   }
  // }
  next();
})
router.afterEach((to, from) => {
  // 跳转路由后，滚动条移到最上面
  window.scroll(0, 0);
})
export default router
