<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
				</el-row>

				<el-row>
													<el-col v-if="user_group == '管理员' || $check_figure('/bonus_items/table')" :span="8">
						<div class="card chart">
									<newLineChart v-if="line_obj_bonus_items.values.length > 0" id="line_obj_bonus_items" :vm="line_obj_bonus_items" :title="'加分项目统计'">
							</newLineChart>
							<div v-if="!line_obj_bonus_items.values.length">加分项目没有符合条件的数据</div>
								</div>
					</el-col>
							<el-col v-if="user_group == '管理员' || $check_figure('/deduction_items/table')" :span="8">
						<div class="card chart">
									<newLineChart v-if="line_obj_deduction_items.values.length > 0" id="line_obj_deduction_items" :vm="line_obj_deduction_items" :title="'扣分项目统计'">
							</newLineChart>
							<div v-if="!line_obj_deduction_items.values.length">扣分项目没有符合条件的数据</div>
								</div>
					</el-col>
							<el-col v-if="user_group == '管理员' || $check_figure('/semester_total/table')" :span="8">
						<div class="card chart">
									<newBarChart v-if="bar_obj_semester_total.values.length > 0" id="bar_obj_semester_total" :vm="bar_obj_semester_total" :title="'学期总计统计'">
							</newBarChart>
							<div v-if="!bar_obj_semester_total.values.length">学期总计没有符合条件的数据</div>
								</div>
					</el-col>
					</el-row>


			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				activeName: "third",
																			line_obj_bonus_items:{
					names:[],
					xAxis: [],
					values:[]
				},
						line_obj_deduction_items:{
					names:[],
					xAxis: [],
					values:[]
				},
						bar_obj_semester_total: {
					names:[],
					xAxis: [],
					values:[]
				},
					url_user_count: "~/api/user/count?",
			};
		},
		created() {
											// 执行加分项目数据获取
			this.get_list_bonus_items();
					// 执行扣分项目数据获取
			this.get_list_deduction_items();
					// 执行学期总计数据获取
			this.get_list_semester_total();
			},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
																		// 获取加分项目统计折线图
			async get_list_bonus_items() {
																													let group_by_value = "student";
				let data = {};
								let flag = true;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																						if (user_group=="学生"){
						sqlwhere+= "student = " + user_id + " or ";
					}
																																																																													if (user_group=="教师"){
						sqlwhere+= "rating_teacher = " + user_id + " or ";
					}
																		if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/bonus_items/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].student);
							}
							this.line_obj_bonus_items.names = name_list;
							this.get_list_bonus_items_sub("student",flag);
						}
				});
																																	},
			async get_list_bonus_items_sub(v1,names_flag) {
																																							let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																						if (user_group=="学生"){
						sqlwhere+= "student = " + user_id + " or ";
					}
																																																																													if (user_group=="教师"){
						sqlwhere+= "rating_teacher = " + user_id + " or ";
					}
																		if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/bonus_items/get_list?groupby=rating_type",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
												xAxis_list.push(list[i].rating_type);
											}
							this.line_obj_bonus_items.xAxis = xAxis_list;
							this.get_list_bonus_items_sub_sub(v1,"rating_type",names_flag,flag);
						}
				});
																							},
			async get_list_bonus_items_sub_sub(v1,v2,names_flag,xAxis_flag) {
																																												let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
								let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																							if (user_group=="学生"){
							sqlwhere+= "student = " + user_id + " or ";
						}
																																																																														if (user_group=="教师"){
							sqlwhere+= "rating_teacher = " + user_id + " or ";
						}
																			if (sqlwhere.length>1){
							sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
							sqlwhere += ")";
							data.sqlwhere = sqlwhere;
						}
									}
								for (let i=0;i<this.line_obj_bonus_items.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_bonus_items.names.length;j++){
						data[v2] = this.line_obj_bonus_items.xAxis[i];
						data[v1] = this.line_obj_bonus_items.names[j];
						await this.$get(
								"~/api/bonus_items/sum?field=bonus_points",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_bonus_items.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_bonus_items.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_bonus_items.xAxis,true);
				}
																		},
					// 获取扣分项目统计折线图
			async get_list_deduction_items() {
																													let group_by_value = "student";
				let data = {};
								let flag = true;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																						if (user_group=="学生"){
						sqlwhere+= "student = " + user_id + " or ";
					}
																																																																													if (user_group=="教师"){
						sqlwhere+= "rating_teacher = " + user_id + " or ";
					}
																		if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/deduction_items/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].student);
							}
							this.line_obj_deduction_items.names = name_list;
							this.get_list_deduction_items_sub("student",flag);
						}
				});
																																	},
			async get_list_deduction_items_sub(v1,names_flag) {
																																							let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																						if (user_group=="学生"){
						sqlwhere+= "student = " + user_id + " or ";
					}
																																																																													if (user_group=="教师"){
						sqlwhere+= "rating_teacher = " + user_id + " or ";
					}
																		if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/deduction_items/get_list?groupby=rating_type",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
												xAxis_list.push(list[i].rating_type);
											}
							this.line_obj_deduction_items.xAxis = xAxis_list;
							this.get_list_deduction_items_sub_sub(v1,"rating_type",names_flag,flag);
						}
				});
																							},
			async get_list_deduction_items_sub_sub(v1,v2,names_flag,xAxis_flag) {
																																												let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
								let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																							if (user_group=="学生"){
							sqlwhere+= "student = " + user_id + " or ";
						}
																																																																														if (user_group=="教师"){
							sqlwhere+= "rating_teacher = " + user_id + " or ";
						}
																			if (sqlwhere.length>1){
							sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
							sqlwhere += ")";
							data.sqlwhere = sqlwhere;
						}
									}
								for (let i=0;i<this.line_obj_deduction_items.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_deduction_items.names.length;j++){
						data[v2] = this.line_obj_deduction_items.xAxis[i];
						data[v1] = this.line_obj_deduction_items.names[j];
						await this.$get(
								"~/api/deduction_items/sum?field=deduction_points",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_deduction_items.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_deduction_items.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_deduction_items.xAxis,true);
				}
																		},
					// 获取学期总计统计柱状图
			async get_list_semester_total() {
				let name_list = [];
				let query_str = "";
									let group_by_value = "student";
								let flag = true;
												let date_flag = "其他"
																																																																																														name_list.push("总分");
				query_str = query_str+"total_score"+","
									this.bar_obj_semester_total.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
								let sqlwhere = "(";
														if (user_group=="学生"){
						sqlwhere+= "student = " + user_id + " or ";
					}
																																																																																																								if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
							}
						await this.$get(
						"~/api/semester_total/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_semester_total.xAxis = xAxis;
								this.bar_obj_semester_total.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_semester_total.xAxis,true);
							}
						});
			},
	
		},
		computed:{
			recognitionHeight(){
				return "830px"
			},
			recognitionUrl(){
				return "https://www.faceplusplus.com.cn/${model.filter.recognitionType}/"
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}

	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
	.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
		width: 100%;
		height: 100%;
	}
	.iframe_box_top{
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100px;
		font-size: 25px;
		line-height: 100px;
		background: #fff;
		z-index: 99999999;
		padding-left: 50px;
	}
	#iframe_box_face div::before {
		content: '';
		width: 100px;
		position: absolute;
		top: 154px;
		right: 129px;
		z-index: 999;
		height: 20px;
		background-color: #FFFFFF;
	}

	#iframe_box_face>h1 {
		margin-top: 100px;
		margin-bottom: 20px;
	}
</style>
