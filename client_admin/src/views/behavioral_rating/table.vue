<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>


							<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="校区名称">
									<el-select v-model="query.campus_name">
											<el-option v-for="o in list_campus_name" :key="o.campus_name" :label="o.campus_name"
								:value="o.campus_name">
							</el-option>
										</el-select>
								</el-form-item>
				</el-col>
									<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="年级名称">
									<el-select v-model="query.grade_name">
											<el-option v-for="o in list_grade_name" :key="o.grade_name" :label="o.grade_name"
								:value="o.grade_name">
							</el-option>
										</el-select>
								</el-form-item>
				</el-col>
									<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="班级名称">
									<el-select v-model="query.class_name">
											<el-option v-for="o in list_class_name" :key="o.class_name" :label="o.class_name"
								:value="o.class_name">
							</el-option>
										</el-select>
								</el-form-item>
				</el-col>
																						<el-col :xs="24" :sm="10" :lg="8" class="search_btn_wrap_1">
					<el-form-item>
						<el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
						<el-button @click="reset()" style="margin-right: 74px;" class="search_btn_reset">重置</el-button>
						<router-link v-if="user_group == '管理员' || $check_action('/behavioral_rating/table','add') || $check_action('/behavioral_rating/view','add')" class="el-button el-button--default el-button--primary search_btn_add" to="./view?">添加
						</router-link>
            			<el-button v-if="user_group == '管理员' || $check_action('/behavioral_rating/table','del') || $check_action('/behavioral_rating/view','del')" class="search_btn_del" type="danger" @click="delInfo()">删除</el-button>
					</el-form-item>
				</el-col>

			</el-row>
		</el-form>
		<el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
				<el-table-column prop="campus_name" @sort-change="$sortChange" label="校区名称"
				v-if="user_group == '管理员' || $check_field('get','campus_name')" min-width="200">
					</el-table-column>
					<el-table-column prop="grade_name" @sort-change="$sortChange" label="年级名称"
				v-if="user_group == '管理员' || $check_field('get','grade_name')" min-width="200">
					</el-table-column>
					<el-table-column prop="class_name" @sort-change="$sortChange" label="班级名称"
				v-if="user_group == '管理员' || $check_field('get','class_name')" min-width="200">
					</el-table-column>
					<el-table-column prop="student" @sort-change="$sortChange" label="学生"
				v-if="user_group == '管理员' || $check_field('get','student')" min-width="200">
						<template slot-scope="scope">
					{{ get_user_student(scope.row['student']) }}
				</template>
					</el-table-column>
					<el-table-column prop="student_id" @sort-change="$sortChange" label="学号"
				v-if="user_group == '管理员' || $check_field('get','student_id')" min-width="200">
					</el-table-column>
					<el-table-column prop="total_score" @sort-change="$sortChange" label="总分数"
				v-if="user_group == '管理员' || $check_field('get','total_score')" min-width="200">
					</el-table-column>
					<el-table-column prop="total_deduction_points" @sort-change="$sortChange" label="总扣分"
				v-if="user_group == '管理员' || $check_field('get','total_deduction_points')" min-width="200">
					</el-table-column>
					<el-table-column prop="actual_score" @sort-change="$sortChange" label="实际分数"
				v-if="user_group == '管理员' || $check_field('get','actual_score')" min-width="200">
					</el-table-column>
	



            <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
            </el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
			</el-table-column>







			<el-table-column fixed="right" label="操作" min-width="120" v-if="user_group == '管理员' || $check_action('/behavioral_rating/table','set') || $check_action('/behavioral_rating/view','set') || $check_action('/behavioral_rating/view','get') || $check_action('/加分|扣分/table','add') || $check_action('/加分|扣分/view','add')" >


				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
					v-if="user_group == '管理员' || $check_action('/behavioral_rating/table','set') || $check_action('/behavioral_rating/view','set') || $check_action('/behavioral_rating/view','get')"
						:to="'./view?' + field + '=' + scope.row[field]"
						 size="small">
						<span>详情</span>
					</router-link>
						<!--跨表按钮-->
							<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="to_table(scope.row,'/bonus_items/view')" v-if="user_group == '管理员' || $check_action('/bonus_items/table','add') || $check_action('/bonus_items/view','add')">
						<span>加分</span>
					</el-button>
							<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="to_table(scope.row,'/deduction_items/view')" v-if="user_group == '管理员' || $check_action('/deduction_items/table','add') || $check_action('/deduction_items/view','add')">
						<span>扣分</span>
					</el-button>
							</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

								
		<div class="modal_wrap" v-if="showModal">
			<div class="modal_box">
				<!-- <div class="modal_box_close" @click="closeModal">X</div> -->
				<p class="modal_box_title">重要提醒</p>
				<p class="modal_box_text">当前有数据达到预警值！</p>
				<div class="btn_box">
					<span @click="closeModal">取消</span>
					<span @click="closeModal">确定</span>
				</div>
			</div>
		</div>


	</el-main>
</template>
<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 弹框
				showModal: false,
				// 获取数据地址
				url_get_list: "~/api/behavioral_rating/get_list?like=0",
				url_del: "~/api/behavioral_rating/del?",

				// 字段ID
				field: "behavioral_rating_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
								"campus_name": "",
											"grade_name": "",
											"class_name": "",
													"login_time": "",
					"create_time": "",
					"orderby": `create_time desc`
				},

				// 数据
				list: [],
													// 校区名称列表
				list_campus_name: [""],
							// 年级名称列表
				list_grade_name: [""],
							// 班级名称列表
				list_class_name: [""],
										// 用户列表
				list_user_student: [],
						// 学号列表
				list_student_id: [""],
											}
		},
		methods: {
			// 关闭弹框
			closeModal(){
				this.showModal = false;
				},

			get_list_before(param){
				var user_group = this.user.user_group;
				if(user_group != "管理员"){
						let sqlwhere = "(";
																									if(user_group=="学生"){
						sqlwhere+= "student = " + this.user.user_id + " or ";
					}
																														if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						param["sqlwhere"] = sqlwhere;
					}
					}
				return param;
			},

				/**
			 * 获取校区名称列表
			 */
			async get_list_campus_name() {
				var json = await this.$get("~/api/campus_management/get_list?");
				if(json.result){
					this.list_campus_name = json.result.list;
				}else if (json.error){
					console.log(json.error);
				}
			},
						/**
			 * 获取年级名称列表
			 */
			async get_list_grade_name() {
				var json = await this.$get("~/api/grade_management/get_list?");
				if(json.result){
					this.list_grade_name = json.result.list;
				}else if (json.error){
					console.log(json.error);
				}
			},
						/**
			 * 获取班级名称列表
			 */
			async get_list_class_name() {
				var json = await this.$get("~/api/class_management/get_list?");
				if(json.result){
					this.list_class_name = json.result.list;
				}else if (json.error){
					console.log(json.error);
				}
			},
								/**
			 * 获取学号列表
			 */
			async get_list_student_id() {
				var json = await this.$get("~/api/student/get_list?");
				if(json.result){
					this.list_student_id = json.result.list;
				}else if (json.error){
					console.log(json.error);
				}
			},
								
			open_tip() {
				const h = this.$createElement;

				var message = "";
				var list = this.list;

				var ifs = [
												];
				for (var n = 0; n < ifs.length; n++) {
					var o = ifs[n];
					for (var i = 0; i < list.length; i++) {
						var lt = list[i];
						if (o.type == "数内") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] > o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "数外") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日内") {
							if ((o.start) && (o.end)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日外") {
							if (o.start && o.end) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						}
					}

					if (o["idx"]) {
						message += o.title;
						if (o["type"] == "数内") {
							if (o.start || o.start === 0) {
								message += "大于" + o.start;
							}
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								message += "并且";
							}
							if (o.end || o.end === 0) {
								message += "小于" + o.end;
							}
						} else if (o["type"] == "数外") {
							if (o.start || o.start === 0) {
								message += "小于" + o.start;
							}
							if ((o.start || o.start === 0) || (o.end || o.end === 0)) {
								message += "或者";
							}
							if (o.end || o.end === 0) {
								message += "大于" + o.end;
							}
						} else if (o["type"] == "日内") {
							if (o.start) {
								message += "在" + o.start + "之后";
							}
							if (o.start && o.end) {
								message += "并且";
							}
							if (o.end) {
								message += "在" + o.end + "之前";
							}
						} else if (o["type"] == "日外") {
							if (o.start) {
								message += "在" + o.start + "之前";
							}
							if (o.start || o.end) {
								message += "或者";
							}
							if (o.end) {
								message += "在" + o.end + "之后";
							}
						}
						message += "的有" + o["idx"] + "条";
					}
				}

				if (message) {
					message += "，需要处理、请尽快处理。";
					this.showModal = true;
					// this.$notify({
					// 	title: '提醒',
					// 	dangerouslyUseHTMLString: true,
					// 	message: h('i', {
					// 		style: 'color: teal'
					// 	}, message)
					// });
				}
			},




										/**
			 * 获取学生用户列表
			 */
			async get_list_user_student() {
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
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
													deleteRow(index, rows) {
				rows.splice(index, 1);
			}

		},
		created() {
				// 初始化校区名称列表
			this.get_list_campus_name();
						// 初始化年级名称列表
			this.get_list_grade_name();
						// 初始化班级名称列表
			this.get_list_class_name();
									this.get_list_user_student();
					// 初始化学号列表
			this.get_list_student_id();
											setTimeout(() => {
				this.open_tip();
			}, 1000)
		}
	}
</script>

<style type="text/css">
	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}


	.modal_wrap{
		width: 100vw;
		height: 100vh;
		position: fixed;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.5);
		z-index: 9999999999;
	}
	.modal_wrap .modal_box{
		width: 400px;
		height: 200px;
		background: url("../../assets/modal_bg.jpg") no-repeat center;
		background-size: cover;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -200px;
		margin-top: -100px;
		border-radius: 10px;
		}
	.modal_wrap .modal_box .modal_box_close{
		font-size: 20px;
		position: absolute;
		top: 10px;
		right: 10px;
		cursor: pointer;
		}
	.modal_wrap .modal_box .modal_box_title{
	  text-align: center;
    font-size: 18px;
    margin: 16px auto;
    color: #fff;
    border-bottom: 1px solid rgba(117, 116, 116,0.5);
    padding-bottom: 16px;
    width: 356px;
		}
	.modal_wrap .modal_box .modal_box_text{
			text-align: center;
		font-size: 15px;
		color: #fff;
		margin-top: 25px;
		}
	.modal_wrap .modal_box .btn_box{
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 42px;
		}
			.modal_wrap .modal_box .btn_box span{
				display: inline-block;
				width: 80px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				border: 1px solid #ccc;
				font-size: 14px;
				cursor: pointer;
				color: #fff;
			}
	.modal_wrap .modal_box .btn_box span:nth-child(2){
		background: #409EFF;
		color: #fff;
		border-color: #409EFF;
		margin-left: 15px;
	}
</style>
