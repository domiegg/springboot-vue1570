<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','student') || $check_field('add','student') || $check_field('set','student')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生" prop="student">
													<el-select v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','student')) || (!form['semester_total_id'] && $check_field('add','student'))" id="student" v-model="form['student']" :disabled="disabledObj['student_isDisabled']">
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
												<el-input id="student_id" v-model="form['student_id']" placeholder="请输入学号"
							  v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','student_id')) || (!form['semester_total_id'] && $check_field('add','student_id'))" :disabled="disabledObj['student_id_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_id')">{{form['student_id']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','campus_name') || $check_field('add','campus_name') || $check_field('set','campus_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="校区名称" prop="campus_name">
								<el-select id="campus_name" v-model="form['campus_name']"
						v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','campus_name')) || (!form['semester_total_id'] && $check_field('add','campus_name'))">
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
						v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','grade_name')) || (!form['semester_total_id'] && $check_field('add','grade_name'))">
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
						v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','class_name')) || (!form['semester_total_id'] && $check_field('add','class_name'))">
						<el-option v-for="o in list_class_name" :key="o['class_name']" :label="o['class_name']"
							:value="o['class_name']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','graded_semester') || $check_field('add','graded_semester') || $check_field('set','graded_semester')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="评分学期" prop="graded_semester">
												<el-input id="graded_semester" v-model="form['graded_semester']" placeholder="请输入评分学期"
							  v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','graded_semester')) || (!form['semester_total_id'] && $check_field('add','graded_semester'))" :disabled="disabledObj['graded_semester_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','graded_semester')">{{form['graded_semester']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','full_score') || $check_field('add','full_score') || $check_field('set','full_score')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="满分" prop="full_score">
												<el-input id="full_score" v-model="form['full_score']" placeholder="请输入满分"
							  v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','full_score')) || (!form['semester_total_id'] && $check_field('add','full_score'))" :disabled="disabledObj['full_score_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_score')">{{form['full_score']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','bonus_points') || $check_field('add','bonus_points') || $check_field('set','bonus_points')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="加分" prop="bonus_points">
												<el-input id="bonus_points" v-model="form['bonus_points']" placeholder="请输入加分"
							  v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','bonus_points')) || (!form['semester_total_id'] && $check_field('add','bonus_points'))" :disabled="disabledObj['bonus_points_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','bonus_points')">{{form['bonus_points']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','deduction_points') || $check_field('add','deduction_points') || $check_field('set','deduction_points')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="扣分" prop="deduction_points">
												<el-input id="deduction_points" v-model="form['deduction_points']" placeholder="请输入扣分"
							  v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','deduction_points')) || (!form['semester_total_id'] && $check_field('add','deduction_points'))" :disabled="disabledObj['deduction_points_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','deduction_points')">{{form['deduction_points']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','total') || $check_field('add','total') || $check_field('set','total')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="合计" prop="total">
												<el-input id="total" v-model="form['total']" placeholder="请输入合计"
							  v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','total')) || (!form['semester_total_id'] && $check_field('add','total'))"  @focus="set_total()" :disabled="disabledObj['total_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total')">{{form['total']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','total_score') || $check_field('add','total_score') || $check_field('set','total_score')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="总分" prop="total_score">
												<el-input id="total_score" v-model="form['total_score']" placeholder="请输入总分"
							  v-if="user_group === '管理员' || (form['semester_total_id'] && $check_field('set','total_score')) || (!form['semester_total_id'] && $check_field('add','total_score'))"  @focus="set_total_score()" :disabled="disabledObj['total_score_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_score')">{{form['total_score']}}</div>
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
				field: "semester_total_id",
				url_add: "~/api/semester_total/add?",
				url_set: "~/api/semester_total/set?",
				url_get_obj: "~/api/semester_total/get_obj?",
				url_upload: "~/api/semester_total/upload?",

				query: {
					"semester_total_id": 0,
				},

				form: {
								"student": 0, // 学生
										"student_id":  '', // 学号
										"campus_name":  '', // 校区名称
										"grade_name":  '', // 年级名称
										"class_name":  '', // 班级名称
										"graded_semester":  '', // 评分学期
										"full_score":  '', // 满分
										"bonus_points":  '', // 加分
										"deduction_points":  '', // 扣分
										"total":  '', // 合计
										"total_score":  '', // 总分
											"semester_total_id": 0, // ID
						
				},
				disabledObj:{
								"student_isDisabled": false,
										"student_id_isDisabled": false,
										"campus_name_isDisabled": false,
										"grade_name_isDisabled": false,
										"class_name_isDisabled": false,
										"graded_semester_isDisabled": false,
										"full_score_isDisabled": false,
										"bonus_points_isDisabled": false,
										"deduction_points_isDisabled": false,
										"total_isDisabled": false,
										"total_score_isDisabled": false,
										},

	
					// 用户列表
				list_user_student: [],
				
						// 校区名称选项列表
				list_campus_name: [""],
	
						// 年级名称选项列表
				list_grade_name: [""],
	
						// 班级名称选项列表
				list_class_name: [""],
	
		
		
		
		
		
		
	
			}
		},
		methods: {


	
	
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
	
			
	
			
	
			
	
			
	
			
	
										set_total(){
				this.form.total = parseFloat(this.form.bonus_points) - parseFloat(this.form.deduction_points)
			},
												
	
															set_total_score(){
				this.form.total_score = parseFloat(this.form.full_score) - parseFloat(this.form.total)
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
					bl = this.$check_action('/semester_total/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/semester_total/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/semester_total/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/semester_total/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/semester_total/view','get');
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
					this.get_list_user_student();
									this.get_list_campus_name();
						this.get_list_grade_name();
						this.get_list_class_name();
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
