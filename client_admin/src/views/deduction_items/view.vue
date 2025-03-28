<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','rating_date') || $check_field('add','rating_date') || $check_field('set','rating_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="评分日期" prop="rating_date">
								<el-date-picker :disabled="disabledObj['rating_date_isDisabled']" v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','rating_date')) || (!form['deduction_items_id'] && $check_field('add','rating_date'))" id="rating_date"
						v-model="form['rating_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','rating_date')">{{form['rating_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','campus_name') || $check_field('add','campus_name') || $check_field('set','campus_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="校区名称" prop="campus_name">
												<el-input id="campus_name" v-model="form['campus_name']" placeholder="请输入校区名称"
							  v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','campus_name')) || (!form['deduction_items_id'] && $check_field('add','campus_name'))" :disabled="disabledObj['campus_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','campus_name')">{{form['campus_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','grade_name') || $check_field('add','grade_name') || $check_field('set','grade_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="年级名称" prop="grade_name">
												<el-input id="grade_name" v-model="form['grade_name']" placeholder="请输入年级名称"
							  v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','grade_name')) || (!form['deduction_items_id'] && $check_field('add','grade_name'))" :disabled="disabledObj['grade_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','grade_name')">{{form['grade_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="班级名称" prop="class_name">
												<el-input id="class_name" v-model="form['class_name']" placeholder="请输入班级名称"
							  v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','class_name')) || (!form['deduction_items_id'] && $check_field('add','class_name'))" :disabled="disabledObj['class_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','student') || $check_field('add','student') || $check_field('set','student')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生" prop="student">
													<el-select v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','student')) || (!form['deduction_items_id'] && $check_field('add','student'))" id="student" v-model="form['student']" :disabled="disabledObj['student_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','student_id')) || (!form['deduction_items_id'] && $check_field('add','student_id'))" :disabled="disabledObj['student_id_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_id')">{{form['student_id']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','rating_type') || $check_field('add','rating_type') || $check_field('set','rating_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="评分类型" prop="rating_type">
								<el-select id="rating_type" v-model="form['rating_type']"
						v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','rating_type')) || (!form['deduction_items_id'] && $check_field('add','rating_type'))">
						<el-option v-for="o in list_rating_type" :key="o['rating_type']" :label="o['rating_type']"
							:value="o['rating_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','rating_type')">{{form['rating_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','deduction_points') || $check_field('add','deduction_points') || $check_field('set','deduction_points')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="扣分分数" prop="deduction_points">
								<el-input-number id="deduction_points" v-model.number="form['deduction_points']"
						v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','deduction_points')) || (!form['deduction_items_id'] && $check_field('add','deduction_points'))" :disabled="disabledObj['deduction_points_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','deduction_points')">{{form['deduction_points']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注信息" prop="remarks">
												<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注信息"
							  v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','remarks')) || (!form['deduction_items_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','rating_teacher') || $check_field('add','rating_teacher') || $check_field('set','rating_teacher')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="评分教师" prop="rating_teacher">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_rating_teacher(form['rating_teacher']) }}
							<!--<el-input id="business_name" v-model="form['rating_teacher']" placeholder="请输入评分教师"-->
							<!--v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','rating_teacher')) || (!form['deduction_items_id'] && $check_field('add','rating_teacher'))" :disabled="disabledObj['rating_teacher_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','rating_teacher')">{{form['rating_teacher']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['deduction_items_id'] && $check_field('set','rating_teacher')) || (!form['deduction_items_id'] && $check_field('add','rating_teacher'))" id="rating_teacher" v-model="form['rating_teacher']" :disabled="disabledObj['rating_teacher_isDisabled']">
								<el-option v-for="o in list_user_rating_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','rating_teacher')" id="rating_teacher" v-model="form['rating_teacher']" :disabled="true">
								<el-option v-for="o in list_user_rating_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="rating_teacher" v-model="form['rating_teacher']" :disabled="disabledObj['rating_teacher_isDisabled']">
							<el-option v-for="o in list_user_rating_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
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
				field: "deduction_items_id",
				url_add: "~/api/deduction_items/add?",
				url_set: "~/api/deduction_items/set?",
				url_get_obj: "~/api/deduction_items/get_obj?",
				url_upload: "~/api/deduction_items/upload?",

				query: {
					"deduction_items_id": 0,
				},

				form: {
								"rating_date":  '', // 评分日期
										"campus_name":  '', // 校区名称
										"grade_name":  '', // 年级名称
										"class_name":  '', // 班级名称
										"student": 0, // 学生
										"student_id":  '', // 学号
										"rating_type":  '', // 评分类型
										"deduction_points":  0, // 扣分分数
										"remarks":  '', // 备注信息
										"rating_teacher": 0, // 评分教师
											"deduction_items_id": 0, // ID
						
				},
				disabledObj:{
								"rating_date_isDisabled": false,
										"campus_name_isDisabled": false,
										"grade_name_isDisabled": false,
										"class_name_isDisabled": false,
										"student_isDisabled": false,
										"student_id_isDisabled": false,
										"rating_type_isDisabled": false,
					          			"deduction_points_isDisabled": false,
										"remarks_isDisabled": false,
										"rating_teacher_isDisabled": false,
										},

	
		
		
		
		
					// 用户列表
				list_user_student: [],
				
						// 评分类型选项列表
				list_rating_type: [""],
	
		
		
		
					// 用户列表
				list_user_rating_teacher: [],
						// 用户组
				group_user_rating_teacher: "",
			
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
			 * 获取评分类型列表
			 */
			async get_list_rating_type() {
				var json = await this.$get("~/api/scoring_items/get_list?");
				if(json.result && json.result.list){
					this.list_rating_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
			
	
			
	
				/**
			 * 获取教师用户列表
			 */
			async get_list_user_rating_teacher() {
                // if(this.user_group !== "管理员" && this.form["rating_teacher"] === 0) {
                //     this.form["rating_teacher"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师");
                if(json.result && json.result.list){
                    this.list_user_rating_teacher = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取教师用户组
			 */
			async get_group_user_rating_teacher() {
							this.form["rating_teacher"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=教师");
				if(json.result && json.result.obj){
					this.group_user_rating_teacher = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_rating_teacher(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_rating_teacher.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["rating_teacher"] = id
									_this.disabledObj['rating_teacher' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "rating_teacher") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_rating_teacher(id){
				var obj = this.list_user_rating_teacher.getObj({"user_id":id});
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
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
																// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
									
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
		        if (this.form["rating_date"].indexOf("-")===-1){
          this.form["rating_date"] = this.$toTime(parseInt(this.form["rating_date"]),"yyyy-MM-dd")
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


		        if(this.form["rating_date"]=="0000-00-00"){
          this.form["rating_date"] = null;
        }
				if(parseInt(this.form["rating_date"]) > 9999){
					this.form["rating_date"] = this.$toTime(parseInt(this.form["rating_date"]),"yyyy-MM-dd")
				}
																													

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
					bl = this.$check_action('/deduction_items/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/deduction_items/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/deduction_items/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/deduction_items/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/deduction_items/view','get');
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
									this.get_list_rating_type();
											this.get_list_user_rating_teacher();
					this.get_group_user_rating_teacher();
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
