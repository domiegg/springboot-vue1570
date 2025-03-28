import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
import register from '../views/register.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 注册
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {
			index: 0,
			title: '注册'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},








	// 教师路由
	{
		path: '/teacher/table',
		name: 'teacher_table',
		component: () => import('../views/teacher/table.vue'),
		meta: {
			index: 0,
			title: '教师列表'
		}
	},
	{
		path: '/teacher/view',
		name: 'teacher_view',
		component: () => import('../views/teacher/view.vue'),
		meta: {
			index: 0,
			title: '教师详情'
		}
	},
	// 学生路由
	{
		path: '/student/table',
		name: 'student_table',
		component: () => import('../views/student/table.vue'),
		meta: {
			index: 0,
			title: '学生列表'
		}
	},
	{
		path: '/student/view',
		name: 'student_view',
		component: () => import('../views/student/view.vue'),
		meta: {
			index: 0,
			title: '学生详情'
		}
	},
	// 校区管理路由
	{
		path: '/campus_management/table',
		name: 'campus_management_table',
		component: () => import('../views/campus_management/table.vue'),
		meta: {
			index: 0,
			title: '校区管理列表'
		}
	},
	{
		path: '/campus_management/view',
		name: 'campus_management_view',
		component: () => import('../views/campus_management/view.vue'),
		meta: {
			index: 0,
			title: '校区管理详情'
		}
	},
	// 班级管理路由
	{
		path: '/class_management/table',
		name: 'class_management_table',
		component: () => import('../views/class_management/table.vue'),
		meta: {
			index: 0,
			title: '班级管理列表'
		}
	},
	{
		path: '/class_management/view',
		name: 'class_management_view',
		component: () => import('../views/class_management/view.vue'),
		meta: {
			index: 0,
			title: '班级管理详情'
		}
	},
	// 年级管理路由
	{
		path: '/grade_management/table',
		name: 'grade_management_table',
		component: () => import('../views/grade_management/table.vue'),
		meta: {
			index: 0,
			title: '年级管理列表'
		}
	},
	{
		path: '/grade_management/view',
		name: 'grade_management_view',
		component: () => import('../views/grade_management/view.vue'),
		meta: {
			index: 0,
			title: '年级管理详情'
		}
	},
	// 评分项目路由
	{
		path: '/scoring_items/table',
		name: 'scoring_items_table',
		component: () => import('../views/scoring_items/table.vue'),
		meta: {
			index: 0,
			title: '评分项目列表'
		}
	},
	{
		path: '/scoring_items/view',
		name: 'scoring_items_view',
		component: () => import('../views/scoring_items/view.vue'),
		meta: {
			index: 0,
			title: '评分项目详情'
		}
	},
	// 行为评分路由
	{
		path: '/behavioral_rating/table',
		name: 'behavioral_rating_table',
		component: () => import('../views/behavioral_rating/table.vue'),
		meta: {
			index: 0,
			title: '行为评分列表'
		}
	},
	{
		path: '/behavioral_rating/view',
		name: 'behavioral_rating_view',
		component: () => import('../views/behavioral_rating/view.vue'),
		meta: {
			index: 0,
			title: '行为评分详情'
		}
	},
	// 加分项目路由
	{
		path: '/bonus_items/table',
		name: 'bonus_items_table',
		component: () => import('../views/bonus_items/table.vue'),
		meta: {
			index: 0,
			title: '加分项目列表'
		}
	},
	{
		path: '/bonus_items/view',
		name: 'bonus_items_view',
		component: () => import('../views/bonus_items/view.vue'),
		meta: {
			index: 0,
			title: '加分项目详情'
		}
	},
	// 扣分项目路由
	{
		path: '/deduction_items/table',
		name: 'deduction_items_table',
		component: () => import('../views/deduction_items/table.vue'),
		meta: {
			index: 0,
			title: '扣分项目列表'
		}
	},
	{
		path: '/deduction_items/view',
		name: 'deduction_items_view',
		component: () => import('../views/deduction_items/view.vue'),
		meta: {
			index: 0,
			title: '扣分项目详情'
		}
	},
	// 学期总计路由
	{
		path: '/semester_total/table',
		name: 'semester_total_table',
		component: () => import('../views/semester_total/table.vue'),
		meta: {
			index: 0,
			title: '学期总计列表'
		}
	},
	{
		path: '/semester_total/view',
		name: 'semester_total_view',
		component: () => import('../views/semester_total/view.vue'),
		meta: {
			index: 0,
			title: '学期总计详情'
		}
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "中学生日常行为评分管理系统-admin";
	document.title = title;
})

export default router
