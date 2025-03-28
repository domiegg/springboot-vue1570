<template>
	<div>
			<el-form-item v-if="$check_register_field('add','job_no','/teacher/view')" label="工号" prop="job_no">
				<el-input id="job_no" v-model="form['job_no']" placeholder="请输入工号"></el-input>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','full_name','/teacher/view')" label="姓名" prop="full_name">
				<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"></el-input>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','gender','/teacher/view')" label="性别" prop="gender">
				<el-select id="gender" v-model="form['gender']">
			<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','campus_name','/teacher/view')" label="校区名称" prop="campus_name">
				<el-select id="campus_name" v-model="form['campus_name']">
			<el-option v-for="o in list_campus_name" :key="o.campus_name" :label="o.campus_name"
				:value="o.campus_name">
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
							"job_no":  '' ,
								"full_name":  '' ,
								"gender":  '' ,
								"campus_name":  '' ,
						}
				}
			},

		},
		data() {
			return {
				field: "teacher_id",
				url_add: "~/api/teacher/add?",
				url_set: "~/api/teacher/set?",
				url_upload: "~/api/teacher/upload?",

							list_gender: ['男','女'],
						list_campus_name: [""],
	
				rules: {
					"job_no": [    {required: true,message: '工号不能为空'},  ],
					"full_name": [    {required: true,message: '姓名不能为空'},  ],
					"gender": [    {required: true,message: '性别不能为空'},  ],
					"campus_name": [    {required: true,message: '校区名称不能为空'},  ],
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
	

		},
		created() {
							this.get_list_campus_name();
			}
	}
</script>
