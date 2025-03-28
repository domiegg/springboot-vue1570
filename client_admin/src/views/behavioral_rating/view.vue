<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','campus_name') || $check_field('add','campus_name') || $check_field('set','campus_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="校区名称" prop="campus_name">
								<el-select id="campus_name" v-model="form['campus_name']"
						v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','campus_name')) || (!form['behavioral_rating_id'] && $check_field('add','campus_name'))">
						<el-option v-for="o in list_campus_name" :key="o['campus_name']" :label="o['campus_name']"
							:value="o['campus_name']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','campus_name')">{{form['campus_name']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','grade_name') || $check_field('add','grade_name') || $check_field('set','grade_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="年级名称" prop="grade_name">
								<el-select id="grade_name" v-model="form['grade_name']"
						v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','grade_name')) || (!form['behavioral_rating_id'] && $check_field('add','grade_name'))">
						<el-option v-for="o in list_grade_name" :key="o['grade_name']" :label="o['grade_name']"
							:value="o['grade_name']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','grade_name')">{{form['grade_name']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="班级名称" prop="class_name">
								<el-select id="class_name" v-model="form['class_name']"
						v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','class_name')) || (!form['behavioral_rating_id'] && $check_field('add','class_name'))">
						<el-option v-for="o in list_class_name" :key="o['class_name']" :label="o['class_name']"
							:value="o['class_name']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','student') || $check_field('add','student') || $check_field('set','student')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生" prop="student">
													<el-select v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','student')) || (!form['behavioral_rating_id'] && $check_field('add','student'))" id="student" v-model="form['student']" :disabled="disabledObj['student_isDisabled']">
							<el-option v-for="o in list_user_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','student')" id="student" v-model="form['student']" :disabled="true">
							<el-option v-for="o in list_user_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','student_id') || $check_field('add','student_id') || $check_field('set','student_id')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学号" prop="student_id">
								<el-select id="student_id" v-model="form['student_id']"
						v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','student_id')) || (!form['behavioral_rating_id'] && $check_field('add','student_id'))">
						<el-option v-for="o in list_student_id" :key="o['student_number']" :label="o['student_number']"
							:value="o['student_number']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','student_id')">{{form['student_id']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','total_score') || $check_field('add','total_score') || $check_field('set','total_score')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="总分数" prop="total_score">
								<el-input-number id="total_score" v-model.number="form['total_score']"
						v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','total_score')) || (!form['behavioral_rating_id'] && $check_field('add','total_score'))" :disabled="disabledObj['total_score_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','total_score')">{{form['total_score']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','total_deduction_points') || $check_field('add','total_deduction_points') || $check_field('set','total_deduction_points')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="总扣分" prop="total_deduction_points">
								<el-input-number id="total_deduction_points" v-model.number="form['total_deduction_points']"
						v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','total_deduction_points')) || (!form['behavioral_rating_id'] && $check_field('add','total_deduction_points'))" :disabled="disabledObj['total_deduction_points_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','total_deduction_points')">{{form['total_deduction_points']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','actual_score') || $check_field('add','actual_score') || $check_field('set','actual_score')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="实际分数" prop="actual_score">
												<el-input id="actual_score" v-model="form['actual_score']" placeholder="请输入实际分数"
							  v-if="user_group === '管理员' || (form['behavioral_rating_id'] && $check_field('set','actual_score')) || (!form['behavioral_rating_id'] && $check_field('add','actual_score'))"  @focus="set_actual_score()" :disabled="disabledObj['actual_score_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','actual_score')">{{form['actual_score']}}</div>
											</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "behavioral_rating_id",
				url_add: "~/api/behavioral_rating/add?",
				url_set: "~/api/behavioral_rating/set?",
				url_get_obj: "~/api/behavioral_rating/get_obj?",
				url_upload: "~/api/behavioral_rating/upload?",

				query: {
					"behavioral_rating_id": 0,
				},

				form: {
								"campus_name":  '', // 校区名称
										"grade_name":  '', // 年级名称
										"class_name":  '', // 班级名称
										"student": 0, // 学生
										"student_id":  '', // 学号
										"total_score":  0, // 总分数
										"total_deduction_points":  0, // 总扣分
										"actual_score":  '', // 实际分数
											"behavioral_rating_id": 0, // ID
						
				},
				disabledObj:{
								"campus_name_isDisabled": false,
										"grade_name_isDisabled": false,
										"class_name_isDisabled": false,
										"student_isDisabled": false,
										"student_id_isDisabled": false,
					          			"total_score_isDisabled": false,
					          			"total_deduction_points_isDisabled": false,
										"actual_score_isDisabled": false,
										},

					// 校区名称选项列表
				list_campus_name: [""],
	
						// 年级名称选项列表
				list_grade_name: [""],
	
						// 班级名称选项列表
				list_class_name: [""],
	
		
					// 用户列表
				list_user_student: [],
								// 学号选项列表
				list_student_id: [""],
	
		
		
		
	
			}
		},
		methods: {


	
				/**
			 * 获取校区名称列表
			 */
			async get_list_campus_name() {
				var json = await this.$get("~/api/campus_management/get_list?");
				if(json.result && json.result.list){
					this.list_campus_name = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
				/**
			 * 获取年级名称列表
			 */
			async get_list_grade_name() {
				var json = await this.$get("~/api/grade_management/get_list?");
				if(json.result && json.result.list){
					this.list_grade_name = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
				/**
			 * 获取班级名称列表
			 */
			async get_list_class_name() {
				var json = await this.$get("~/api/class_management/get_list?");
				if(json.result && json.result.list){
					this.list_class_name = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
				/**
			 * 获取学生用户列表
			 */
			async get_list_user_student() {
                // if(this.user_group !== "管理员" && this.form["student"] === 0) {
                //     this.form["student"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_student = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_student(id){
				var obj = this.list_user_student.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
				/**
			 * 获取学号列表
			 */
			async get_list_student_id() {
				var json = await this.$get("~/api/student/get_list?");
				if(json.result && json.result.list){
					this.list_student_id = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
			
	
			
	
										set_actual_score(){
				this.form.actual_score = parseFloat(this.form.total_score) - parseFloat(this.form.total_deduction_points)
			},
						
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
																
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
																				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																								

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																														return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/behavioral_rating/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/behavioral_rating/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/behavioral_rating/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/behavioral_rating/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/behavioral_rating/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
				this.get_list_campus_name();
						this.get_list_grade_name();
						this.get_list_class_name();
							this.get_list_user_student();
							this.get_list_student_id();
										},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
