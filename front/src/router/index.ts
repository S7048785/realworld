import { createRouter, createWebHistory } from 'vue-router'

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
      component: () => import('@/views/Index.vue'),
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
        }
      ]
    },
  ],
})

export default router
