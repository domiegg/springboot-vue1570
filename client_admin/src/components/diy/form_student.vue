<template>
	<div>
			<el-form-item v-if="$check_register_field('add','student_number','/student/view')" label="学号" prop="student_number">
				<el-input id="student_number" v-model="form['student_number']" placeholder="请输入学号"></el-input>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','full_name','/student/view')" label="姓名" prop="full_name">
				<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"></el-input>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','gender','/student/view')" label="性别" prop="gender">
				<el-select id="gender" v-model="form['gender']">
			<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','campus_name','/student/view')" label="校区名称" prop="campus_name">
				<el-select id="campus_name" v-model="form['campus_name']">
			<el-option v-for="o in list_campus_name" :key="o.campus_name" :label="o.campus_name"
				:value="o.campus_name">
			</el-option>
		</el-select>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','class_name','/student/view')" label="班级名称" prop="class_name">
				<el-select id="class_name" v-model="form['class_name']">
			<el-option v-for="o in list_class_name" :key="o.class_name" :label="o.class_name"
				:value="o.class_name">
			</el-option>
		</el-select>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','grade_name','/student/view')" label="年级名称" prop="grade_name">
				<el-select id="grade_name" v-model="form['grade_name']">
			<el-option v-for="o in list_grade_name" :key="o.grade_name" :label="o.grade_name"
				:value="o.grade_name">
			</el-option>
		</el-select>
				</el-form-item>
	
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		props:{

			form:{
				type: Object,
				default: function(){
					return {
							"student_number":  '' ,
								"full_name":  '' ,
								"gender":  '' ,
								"campus_name":  '' ,
								"class_name":  '' ,
								"grade_name":  '' ,
						}
				}
			},

		},
		data() {
			return {
				field: "student_id",
				url_add: "~/api/student/add?",
				url_set: "~/api/student/set?",
				url_upload: "~/api/student/upload?",

							list_gender: ['男','女'],
						list_campus_name: [""],
						list_class_name: [""],
						list_grade_name: [""],
	
				rules: {
					"student_number": [    {required: true,message: '学号不能为空'},  ],
					"full_name": [    {required: true,message: '姓名不能为空'},  ],
					"gender": [    {required: true,message: '性别不能为空'},  ],
					"campus_name": [    {required: true,message: '校区名称不能为空'},  ],
					"class_name": [    {required: true,message: '班级名称不能为空'},  ],
					"grade_name": [    {required: true,message: '年级名称不能为空'},  ],
				}

			}
		},
		methods: {

	
	
	

	
	
	

	
	
	

	
	
				/**
			 * 获取校区名称列表
			 */
			async get_list_campus_name() {
				var list = await this.$get("~/api/campus_management/get_list?");
				this.list_campus_name = list.result.list;
			},
	

	
	
				/**
			 * 获取班级名称列表
			 */
			async get_list_class_name() {
				var list = await this.$get("~/api/class_management/get_list?");
				this.list_class_name = list.result.list;
			},
	

	
	
				/**
			 * 获取年级名称列表
			 */
			async get_list_grade_name() {
				var list = await this.$get("~/api/grade_management/get_list?");
				this.list_grade_name = list.result.list;
			},
	

		},
		created() {
							this.get_list_campus_name();
					this.get_list_class_name();
					this.get_list_grade_name();
			}
	}
</script>
