$(document).ready(function() {
	$.ajaxSetup({
		cache: false
	});

	$('.alert .close').on('click', function(e) {
		$(this).parent().hide();
	});

	$('#query').on("click", function() {
		getAll(null, null);
	});

	//init Service and template
	var userService = new Service("account");
	var template = $('#template').html();
	Mustache.parse(template); //optional, speeds up future uses

	//1.event bind
	$('#target').on("click", "button[name='delete']", remove);

	$('#saveOrUpdate').on("click", updateOrInsert);
	$('#save').on("click", updateOrInsert);

	//$('#target').on("click", '#aliPay', function () {
	//    $.get("pay/llpay/getForm", {"id": "7f0cf9d8-fd22-4eed-8584-a9b6ab908e9b"}, function (data) {
	//        $("#aliPay").html(data.data);
	//    });
	//});

	//登录
	$('#logDiv').on("click", "#login", function() {
		var user = $("#login-form").serializeObject();
		var userService = new Service("account/login");
		userService.getJsonP(user, function() {
			getAll(1);
			showModalAlert("登录成功!")
		})
	});
	//end-登录

	//登出
	$('#logout').on("click", function() {
		var userService = new Service("account/logout");
		userService.getJsonP({}, function() {
			window.location.href = "login.html";
		})
	});
	//end-登出

	//分配角色
	$('#consumeMenuDiv').on("click", "#consumeMenu", function() {
		var accountId = $("#accountId").val().split(",");
		var roleId = $("#roleId").val();
		var accountService = new Service("account/role/" + accountId);
        accountService.add({ ids:[roleId] }, function() {
			showModalAlert("分配角色成功!")
		});
	});
	//end消费套餐

	//批量订阅试用免费套餐
	$('#batchSubscribeFreeMenu').on("click", function() {
		Confirm({
			msg: '是否要继续？',
			onOk: function() {
				var freeMenuService = new Service("account/freemenus");
				freeMenuService.getAll({}, function() {
					showModalAlert("批量订阅试用免费套餐成功!");
				})
			},
			onCancel: function() {
				return;
			}
		})
	});
	//end批量订阅试用免费套餐

	$('#Order-accountId,#Coupon-accountId,#data-accountId,#data-accountId2').on("focus", function() {
		$('#searchAccountDiv').modal('show');
		initAccountTable();
	})

	$('#selectAccount').on('click', function() {
		$('#data-accountId2').val(window.uuid);
		$('#data-accountId').val(window.uuid);
		$('#Order-accountId').val(window.uuid);
		$('#Coupon-accountId').val(window.uuid);
		$('#searchAccountDiv').modal('hide');
	})

	$('#searchAccountBtn').on('click', function() {
		initAccountTable();
	})

	function initAccountTable() {
		var name = $('#searchName').val();
		var mobile = $('#searchMobile').val();
		var email = $('#searchEmail').val();
		if(name || mobile || email) {
			$('#account-list-table').bootstrapTable('destroy').bootstrapTable({
				method: 'get',
				url: 'account/getAccount',
				pagination: true,
				sidePagination: "server", // 服务端分页
				queryParamsType: 'limit',
				queryParams: function(params) {
					var type = $('#typeSelect option:selected').val();
					var newParams = {
						page: parseInt(params.offset / params.limit) + 1,
						pageSize: params.limit,
						name: name,
						mobile: mobile,
						email: email,
						type: type
					};
					return newParams;
				},
				pageSize: 10,
				pageNumber: 1,
				pageList: [10, 25],
				paginationFirstText: "首页",
				paginationPreText: "上一页",
				paginationNextText: "下一页",
				paginationLastText: "最后一页",
				responseHandler: function(data) {
					// 解析服务端数据，组装bootstrap需要的数据集
					var successData = "";
					if(data == null) {
						return data;
					} else if(data.success) {
						successData = proveTableResole(data);
					}
					return successData;
				},
				onLoadSuccess: function(data) {

				},
				onLoadError: function(data) {

				},
				onClickRow: function(row) {
					window.uuid = row.accountUid;
				},
				onCheck: function(row) {
					window.uuid = row.accountUid;
				}
			});
		}
	}

	function proveTableResole(data) {
		var total = data.total;
		var rows = data.data;
		for(var i in rows) {
			var createTime = new Date(rows[i].createTime);
			rows[i].createTime = createTime.getFullYear() + '-' + (createTime.getMonth() + 1) + '-' + createTime.getDate();
		}
		var desData = {
			total: total,
			rows: rows
		};
		return desData;
	}

	//给特定用户补订单
	$('#createAndUpdateOrderDiv').on("click", "#createAndUpdateOrder", function() {
		$('#createAndUpdateOrder').prop('disabled', true);
		function removeDisable() {
			$('#createAndUpdateOrder').prop('disabled', false);
		}
		var accountId = $("#Order-accountId").val();
		var menuId = $("#Order-menuId").val();
		var price = $("#Order-price").val();
		var totalSignNum = $("#Order-totalSignNum").val();

		var validDate = new Date();
		var validDateTemp = $("#Order-validDate").val();
		if(validDateTemp) {
			validDate = new Date(validDateTemp);
		}
		validDate.setHours(0);

		var endDate = $("#Order-endDate").val();
		if(endDate) {
			endDate = new Date(endDate);
		}
		var duration;
		if(validDate && endDate) {
			endDate.setHours(0);
			duration = endDate.getTime() - validDate.getTime();
		}

		var subscribeService = new Service("accountmenus/subscribe");
		var menuIdUrl = menuId + "?accountId=" + accountId;
		subscribeService.updateEx(menuIdUrl, { menuId: menuId, times: 1 }, function(response) {
			if(response.data) {
				var orderId = response.data;
				var orderService = new Service("accountmenus");
				orderService.update({ id: orderId, price: price, validTime: validDate, totalSignNum: totalSignNum, duration: duration }, function() {
					showModalAlert("给特定用户补订单成功!");
					$('#createAndUpdateOrderDiv').modal('hide')
				});
			}
		}).fail(removeDisable);
	});
	//end给特定用户补订单

	//给特定用户分配优惠券
	$('#addCouponDiv').on("click", "#addCoupon", function() {
		var accountId = $("#Coupon-accountId").val();
		var menuId = $("#Coupon-menuId").val();
		var validDate = new Date();
		var validDateTemp = $("#Coupon-validDate").val();
		if(validDateTemp) {
			validDate = new Date(validDateTemp);
		}

		var addCouponService = new Service("billing/addCoupon");
		addCouponService.update({ id: accountId, menuId: menuId, validTime: validDate }, function() {
			showModalAlert("特定用户分配优惠券成功!")
		})

	})
	//end给特定用户分配优惠券

	//订阅试用套餐
	$('#subscribeFreeMenuDiv').on("click", "#subscribeFreeMenu", function() {
		var accountId = $("#data-accountId").val();
		var productType = $("#data-productType").val();
		var signService = new Service("account/freemenu");
		signService.update({ id: accountId, productType: productType }, function() {
			showModalAlert("订阅试用套餐成功！")
		});
	});
	//end订阅试用套餐

	$('#updateDiv').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var id = button.data('id');
		var modal = $(this);

		function callback(data) {
			var user = data && data.data;
			modal.find('.modal-body #data-id').val(user && user.id);
			modal.find('.modal-body #data-name').val(user && user.name);
			modal.find('.modal-body #data-rawPrice').val(user && user.rawPrice);
			modal.find('.modal-body #data-price').val(user && user.price);
			modal.find('.modal-body #data-signNum').val(user && user.totalSignNum);
			modal.find('.modal-body #data-type').val(user && user.type);
			modal.find('.modal-body #data-state').val(user && user.state);
			modal.find('.modal-body #data-discription').val(user && user.discription);
			modal.find('.modal-body #data-duration').val(user && user.duration);
		}

		if(id) {
			//edit
			getById(id, callback);
		} else {
			//add
			callback();
		}

	});

	//pagination
	var pager = $("#pagination").paging(0, {
		format: '[< nncnn >]',
		onClick: function(ev) {
			var page = $(this).data('page');
			if(pager.a.page != page) {
				getAll(page);
			}
			pager.setPage(page);
		},
		page: null,
		onSelect: function(page) {},
		perpage: 10,
		onFormat: function(type) {
			switch(type) {
				case 'block': // n and c
					if(this.value == this.page) {
						return '<a class="page current">' + this.value + '</a>';
					}
					return '<a class="page">' + this.value + '</a>';
				case 'next': // >
					return '<a class="page">下一页</a>';
				case 'prev': // <
					return '<a class="page">上一页</a>';
				case 'first': // [
					return '<a class="page">第一页</a>';
				case 'last': // ]
					return '<a class="page">最后一页</a>';
			}
		}
	});

	//2.define main event handler
	//2.1 getAll
	function getAllCallback(response) {
		for(var index in response.data){
			var roles = [];
			for(var roleR in response.data[index].accountRoleRs){
				roles.push(response.data[index].accountRoleRs[roleR].role.name);
			}
            response.data[index].roles = roles;
		}
		var rendered = Mustache.render(template, response);
		$('#target').html(rendered);
		pager.setNumber(response.total);
		if(!pager.a || !pager.a.page) {
			pager.setPage(1);
		}
		pager.setPage();
	}

	function getAll(page, pageSize) {
		if(typeof page === "object") {
			//for insert or update
			page = pager.a.page;
			pageSize = pager.a.perpage;
		} else {
			pageSize = pageSize || pager.a.perpage;
		}
		var keyword = $('#keyword').val();
		userService.getAll({ page: page, pageSize: pageSize, name: keyword, productType: null }, getAllCallback);
	}

	//2.2 updateOrInsert
	function updateOrInsert() {
		console.log($(this));
		var user = $("#form").serializeObject();
		if(user.id) {
			userService.update(user, getAll);
		} else {
			userService.add(user, getAll);
		}
		$('#updateDiv').modal('hide');
	}

	//2.3 remove
	function remove() {
		console.log($(this));
		var id = $(this).parent().attr("data-id");
		Confirm({
			msg: '确定删除？',
			onOk: function() {
				userService.remove(id, getAll);
			},
			onCancel: function() {
				return;
			}
		})
	}

	//2.4 getById
	function getById(id, callback) {
		console.log($(this));
		userService.getById(id, callback);
	}

	//begin get data
	getAll(1);
});