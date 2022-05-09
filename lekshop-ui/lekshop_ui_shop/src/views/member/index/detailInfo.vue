<template>
  <div class="app-container">
    <el-input v-model="searchParams.uid" clearable placeholder="" style="width: 250px;" class="filter-item" @keyup.enter.native="getMember(true)">
      <template slot="prepend">用户ID</template>
    </el-input>
    <el-input v-model="searchParams.phone" clearable placeholder="" style="width: 250px;" class="filter-item" @keyup.enter.native="getMember(true)">
      <template slot="prepend">手机号码</template>
    </el-input>
    <el-input v-model="searchParams.inviteCode" clearable placeholder="" style="width: 250px;" class="filter-item" @keyup.enter.native="getMember(true)">
      <template slot="prepend">邀请码</template>
    </el-input>
    <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getMember(true)" />

    <br>
    <br>
    <el-divider><h3>基本信息</h3></el-divider>

    <div class="contain">
      <div class="bg-color-w b-color-4 wrap-info-table">
        <div class="flex f-a-c h-50 f-j-c bg-color-4">
          <span v-if="memberDetail.type == 1">买家</span>
                  &nbsp;&nbsp;

          <span v-if="memberDetail.type == 2">商家</span>
                  &nbsp;&nbsp;
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">头像</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <el-avatar shape="square" :src="memberDetail.headImg" />
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">用户ID</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.uid }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">用户名</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.code }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">会员组</div>
            <div class="flex flex-1 f-j-c f-a-c">
              <el-select v-model="memberDetail.groupId" placeholder="请选择组别" class="filter-item" clearable filterable style="width: 200px;" @change="updateMember({id:memberDetail.id, groupId: memberDetail.groupId}, '修改成功')">
                <el-option
                  v-for="item in groupOptions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </div>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">身份证</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberRealname.idCard }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">姓名</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberRealname.realname }}</span>
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">身份认证状态</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.realnameStatus == 0 ? '未认证' : '已认证' }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">用户状态</div>
            <div class="flex flex-1 f-j-c f-a-c">{{ memberDetail.status | memberStatusFilter }}<span v-if="memberDetail.freezeReason">/{{ memberDetail.freezeReason }}</span></div>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">手机号码</div>
            <div class="flex flex-1  f-j-c f-a-c border-r">
              <el-input v-model="memberDetail.phone" style="width:90%;" placeholder="" @change="updateMember({id:memberDetail.id, phone: memberDetail.phone}, '修改成功')" />
            </div>
          </div>
          <div class="flex flex-1 f-s-0 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">QQ号</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <el-input v-model="memberDetail.qq" style="margin-left: 10px; width:90%;" placeholder="" @change="updateMember({id:memberDetail.id, phone: memberDetail.qq}, '修改成功')" />
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">积分</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberAccount.point }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">设备号</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberDetail.imeId }}</span>
            </div>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">注册时间</div>
            <div v-if="memberDetail.addTime" class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">注册IP</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberDetail.registerIp }}</span>
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">最后登录时间</div>
            <div v-if="memberDetail.lastLoginTime" class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.lastLoginTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">最后登录IP</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberDetail.lastLoginIp }}</span>
            </div>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">邀请人ID</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <el-input v-model="memberDetail.tjrUid" style="margin-left: 10px; width:90%;" placeholder="" @change="updateInvite" />
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">银行卡认证状态</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.bankStatus | statusFilter }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">邀请码</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span v-if="memberDetail.inviteStatus == 1">{{ memberDetail.inviteCode }}</span>
              <span v-if="memberDetail.inviteStatus == 0">无邀请资格</span>
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r"></div>
            <div class="flex flex-1 f-j-c f-a-c">
            </div>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">操作</div>
          <div class="flex flex-1 bg-color-w f-w" style="padding: 0 0px 10px 10px;">
            <el-button style="margin: 10px 10px 0 0;" size="small" type="primary" @click="handleAccount">充值/扣钱</el-button>
            <el-popconfirm
              v-if="memberDetail.status == '1' "
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定冻结用户？"
              @confirm="dialogFreezeVisible=true"
            >
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">冻结用户</el-button>
            </el-popconfirm>
            <el-popconfirm
              v-if="memberDetail.status == '-1' || memberDetail.status == '-2'"
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定解冻用户？"
              @confirm="updateMember({id:memberDetail.id, status: '1'}, '解冻成功')"
            >
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">解冻用户</el-button>
            </el-popconfirm>
            <el-popconfirm
              v-if="memberDetail.inviteStatus == 1 "
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定删除邀请资格？"
              @confirm="updateMember({id:memberDetail.id, inviteStatus: 0}, '删除成功')"
            >
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">删除邀请资格</el-button>
            </el-popconfirm>
            <el-popconfirm
              v-if="memberDetail.inviteStatus == 0 "
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定恢复邀请资格？"
              @confirm="updateMember({id:memberDetail.id, inviteStatus: 1}, '恢复成功')"
            >
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">恢复邀请资格</el-button>
            </el-popconfirm>
            <el-popconfirm
              v-if="memberDetail.status != '-2' "
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定注销用户？"
              @confirm="updateMember({id:memberDetail.id, status: '-2'}, '注销成功')"
            >
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">注销用户</el-button>
            </el-popconfirm>
            <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary" @click="handleApplyWithdraw">用户提现</el-button>
            <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary" @click="handleTransfer">本金互转</el-button>
            <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary" @click="handleSendMsg">私信</el-button>
            <el-popconfirm
              v-if="memberDetail.withdrawLimit == 0 "
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定限制该用户提现, 限制后正在申请的提现会被驳回？"
              @confirm="limitWithdraw({memberId:memberDetail.id, status: 1}, '限制成功')"
            >
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">限制提现</el-button>
            </el-popconfirm>
            <el-popconfirm
              v-if="memberDetail.withdrawLimit == 1 "
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定解除用户限制提现？"
              @confirm="limitWithdraw({memberId:memberDetail.id, status: 0}, '解除成功')"
            >
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">解除限制提现</el-button>
            </el-popconfirm>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">备注</div>
            <div class="flex flex-1 f-j-c f-a-c ">
              <el-input v-model="memberDetail.remark" style="margin-left: 10px;" size="mini" type="textarea" placeholder="" @change="updateMember({id:memberDetail.id, remark: memberDetail.remark}, '修改成功')" />
            </div>
          </div>
          <div class="flex flex-1 h-50" />
          <div class="flex flex-1 h-50" />
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10  border-r" />
            <div class="flex flex-1 f-j-c f-a-c">
              <el-tooltip class="item" effect="dark" content="初始密码为123456" placement="top-start">
                <el-popconfirm
                  confirm-button-text="确定"
                  cancel-button-text="不用了"
                  icon="el-icon-info"
                  icon-color="red"
                  title="确定将用户重置初始密码, 初始密码为123456？"
                  @confirm="initPassword"
                >
                  <el-button slot="reference" size="small" type="text">初始化登录密码</el-button>
                </el-popconfirm>
              </el-tooltip>
            </div>
          </div>
        </div>
      </div>
    </div>

    <el-dialog title="充值扣钱" :visible.sync="dialogAccountVisible" width="40%" center>
      <el-form ref="accountForm" :model="accountForm" label-width="120px" label-position="right">
        <el-form-item label="账户资金" prop="amount">
          <code>
            <span>账户余额:￥{{ memberAccount.amount }}</span>
            <br>
            <span>账户积分:￥{{ memberAccount.point }}</span>
            <br>
            <span>推广佣金:￥{{ memberAccount.spmission }}</span>
          </code>
        </el-form-item>
        <el-form-item label="增减余额" prop="amount">
          <el-input v-model="accountForm.amount" placeholder="请填写金额" style="width: 300px;" />
          <span class="tips">*增加金额请填写正数,减少金额请填写负数</span>
        </el-form-item>
        <el-form-item label="增减积分" prop="point">
          <el-input v-model="accountForm.point" placeholder="请填写积分" style="width: 300px;" />
          <span class="tips">*增加积分请填写正数,减少积分请填写负数</span>
        </el-form-item>
        <el-form-item label="增减推广佣金" prop="spmission">
          <el-input v-model="accountForm.spmission" placeholder="请填写金额" style="width: 300px;" />
          <span class="tips">*增加推广佣金请填写正数,减少推广佣金请填写负数</span>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="accountForm.remark" type="textarea" placeholder="备注" style="width: 300px;" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogAccountVisible=false">取消</el-button>
        <el-button type="primary" @click="updateMemberAccount">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="申请提现" :visible.sync="dialogApplyWithdrawVisible" width="30%" center>
      <el-form ref="applyWithdrawForm" :rules="applyWithdrawRules" :model="applyWithdraw" label-width="80px" label-position="right">
        <el-form-item label="可用余额" prop="amount">
          <span class="tips">￥{{ memberAccount.amount }}</span>
        </el-form-item>
        <el-form-item label="收款姓名" prop="accountName">
          <el-input v-model="applyWithdraw.accountName" placeholder="请输入收款姓名" />
        </el-form-item>
        <el-form-item label="提现银行" prop="bank">
          <el-input v-model="applyWithdraw.bank" placeholder="请输入提现银行" />
        </el-form-item>
        <el-form-item label="银行卡号" prop="bankAccount">
          <el-input v-model="applyWithdraw.bankAccount" placeholder="请输入银行卡号" />
        </el-form-item>
        <el-form-item label="提现金额" prop="amount">
          <el-input v-model="applyWithdraw.amount" placeholder="请输入提现金额" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogApplyWithdrawVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmApplyWithdraw">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="本金互转" :visible.sync="dialogTransferVisible" width="30%" center>
      <el-form ref="transferForm" :rules="transferRules" :model="transferForm" label-width="80px" label-position="right">
        <el-form-item label="可用余额" prop="">
          <span class="tips">￥{{ memberAccount.amount }}</span>
        </el-form-item>
        <el-form-item label="收款人ID" prop="uid">
          <el-input v-model="transferForm.uid" placeholder="请输入收款人ID" />
        </el-form-item>
        <el-form-item label="转账金额" prop="amount">
          <el-input v-model="transferForm.amount" placeholder="请输入转账金额" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogTransferVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmTransfer">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="私信" :visible.sync="dialogMsgVisible" width="30%" center>
      <el-form ref="msgForm" :rules="msgRules" :model="msgForm" label-width="80px" label-position="right">
        <el-form-item label="收件人" prop="">
          <span class="tips">{{ memberDetail.code }}</span>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="msgForm.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="msgForm.content" type="textarea" :rows="6" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogMsgVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmSendMsg">发送</el-button>
      </div>
    </el-dialog>

    <el-dialog title="冻结用户" :visible.sync="dialogFreezeVisible" width="30%" center>
      <el-form ref="freezeForm" :rules="freezeRules" :model="freezeForm" label-width="80px" label-position="right">
        <el-form-item label="冻结理由" prop="freezeReason">
          <el-input v-model="freezeForm.freezeReason" type="textarea" :rows="6" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogFreezeVisible=false">取消</el-button>
        <el-button type="primary" @click="updateMember({id:memberDetail.id, status: '-1', freezeReason: freezeForm.freezeReason}, '冻结成功'); dialogFreezeVisible=false ">确认冻结</el-button>
      </div>
    </el-dialog>

    <br>
    <br>
    <el-divider><h3>资金账户信息</h3></el-divider>
    <div class="contain">
      <div class="bg-color-w b-color-4 wrap-info-table">
        <div class="flex f-a-c h-50 f-j-c bg-color-4">
          <span>资金账户</span>
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">余额账户</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              ￥ {{ memberAccount.amount | moneyFormat }}
                      &nbsp;&nbsp;&nbsp;
              <router-link :to="'/fund/account?memberName='+memberDetail.code">
                <span class="link-type">查看流水</span>
              </router-link>
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">冻结余额</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">￥ {{ memberAccount.freezeAmount | moneyFormat }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">推广佣金账户</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <div class="flex flex-1 f-j-c f-a-c border-r">￥ {{ memberAccount.spmission | moneyFormat }}
                <router-link :to="'/fund/spmission?memberName='+memberDetail.code">
                         &nbsp;&nbsp;&nbsp;
                  <span class="link-type">查看流水</span>
                </router-link>
              </div>
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">今日总收入</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">￥ {{ memberAccount.todayIncome | moneyFormat }}</div>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">总收入</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">￥ {{ memberAccount.totalIncome | moneyFormat }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">总支出</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <div class="flex flex-1 f-j-c f-a-c border-r">￥ {{ memberAccount.totalOutCome | moneyFormat }}</div>
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">总充值金额</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">￥ {{ memberAccount.totalRecharge | moneyFormat }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">总提现金额</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">￥ {{ memberAccount.totalWithdraw | moneyFormat }}</div>
          </div>
        </div>
      </div>
    </div>

    <br>
    <br>
    <el-divider><h3>最近提现记录</h3></el-divider>
    <!--提现模块-->
    <el-table
      ref="withdrawTable"
      :key="withdrawTableKey"
      v-loading="withdrawListLoading"
      :data="withdrawList"
      border
      fit
      size="mini"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="提现记录" prop="" align="center">
        <template slot="header" slot-scope="scope">
          提现记录
          <router-link :to="'/fund/withdraw?memberName='+memberDetail.code">
            <span class="link-type">查看更多</span>
          </router-link>
        </template>
        <el-table-column label="订单号" prop="orderNo" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.orderNo }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户名" align="center" prop="memberName">
          <template slot-scope="scope">
            <span>{{ scope.row.memberName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="出款类型" align="center" prop="method">
          <template slot-scope="scope">
            <p>{{ scope.row.method == 1 ? '账户余额' : '佣金余额' }}</p>
          </template>
        </el-table-column>
        <el-table-column label="提现方式" align="center" prop="type">
          <template slot-scope="scope">
            <p>{{ scope.row.type | typeFilter }}</p>
          </template>
        </el-table-column>
        <el-table-column label="银行卡信息" align="center">
          <el-table-column label="开户银行" align="center" prop="bank">
            <template slot-scope="scope">
              <p v-if="scope.row.type == 1">{{ scope.row.bank }}</p>
            </template>
          </el-table-column>
          <el-table-column label="开户账号" align="center" prop="bankAccount">
            <template slot-scope="scope">
              <p v-if="scope.row.type == 1">{{ scope.row.bankAccount }}</p>
            </template>
          </el-table-column>
          <el-table-column label="户名" align="center" prop="realname">
            <template slot-scope="scope">
              <p v-if="scope.row.type == 1">{{ scope.row.realname }}</p>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column label="支付宝账号" align="center" prop="bankAccount">
          <template slot-scope="scope">
            <p v-if="scope.row.type == 2 && scope.row.bankAccount">{{ scope.row.bankAccount }}</p>
          </template>
        </el-table-column>
        <el-table-column label="二维码" align="center" prop="bankAccount">
          <template slot-scope="scope">
            <el-image
              v-if="scope.row.type == 3 && scope.row.bankAccount"
              style="width: 100px; "
              :src="scope.row.bankAccount"
              :preview-src-list="[scope.row.bankAccount]"
            />
          </template>
        </el-table-column>
        <el-table-column label="申请提现金额" align="center" prop="totalAmount">
          <template slot-scope="scope">
            <p style="color:red;">{{ scope.row.totalAmount | moneyFormat }}</p>
          </template>
        </el-table-column>
        <el-table-column label="提现手续费" align="center" prop="fee">
          <template slot-scope="scope">
            <p style="color:red;">{{ scope.row.fee | moneyFormat }}</p>
          </template>
        </el-table-column>
        <el-table-column label="实际到账金额" align="center" prop="amount">
          <template slot-scope="scope">
            <p style="color:red;">{{ scope.row.amount | moneyFormat }}</p>
          </template>
        </el-table-column>
        <el-table-column label="申请/审核时间" align="center" prop="addTime">
          <template slot-scope="scope">
            <span v-if="scope.row.addTime">申请时间: {{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            <br>
            <span v-if="scope.row.updTime && scope.row.status == '1'">审核时间: {{ scope.row.updTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <el-tag>{{ scope.row.status | statusFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="备注" prop="remark" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" align="center">
          <template slot-scope="scope">
            <el-popconfirm
              v-if="scope.row.status == '0'"
              confirm-button-text="确定"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定将该笔提现设为已处理？"
              @confirm="setDoingWithdraw(scope.row)"
            >
              <el-link slot="reference" type="primary">处理中</el-link>
            </el-popconfirm>
            <el-link v-if="scope.row.status == '3'" type="primary" @click="handleVerifyWithdraw(scope.row)">审核</el-link>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>

    <!--审核框-->
    <el-dialog title="审核提现" :visible.sync="dialogWithdrawVisible">
      <el-form ref="withdrawForm" :rules="withdrawRules" :model="withdraw" label-width="80px" label-position="right">
        <el-form-item label="审核结果" prop="status">
          <el-radio-group v-model="withdraw.status">
            <el-radio label="1" border>通过</el-radio>
            <el-radio label="2" border>不通过</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider>拒绝原因</el-divider>
        <el-form-item label="拒绝原因" prop="reasonCheck">
          <el-select v-model="withdraw.reasonCheck" placeholder="请选择" style="width: 100%;" clearable @change="$set(withdraw,'remark',withdraw.reasonCheck);">
            <el-option
              key="0"
              value="银行卡填写错误"
              label="银行卡填写错误"
            />
            <el-option
              key="10"
              value="其他"
              label="其他"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="填写原因" prop="remark">
          <el-input v-model="withdraw.remark" type="textarea" placeholder="请输入拒绝理由" :rows="6" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogWithdrawVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmWithdraw">确定</el-button>
      </div>
    </el-dialog>

    <br>
    <br>
    <el-divider><h3>绑定银行账户信息</h3></el-divider>
    <el-table
      :key="bankTableKey"
      v-loading="bankListLoading"
      :data="bankList"
      border
      fit
      size="mini"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="银行卡列表" prop="" align="center">
        <template slot="header" slot-scope="scope">
          银行卡列表
          <router-link :to="'/member/bank?memberName='+memberDetail.code">
            <span class="link-type">查看更多</span>
          </router-link>
        </template>
        <el-table-column label="用户名" prop="memberName" align="center">
          <template slot-scope="scope">
            <p>{{ scope.row.memberName }}</p>
          </template>
        </el-table-column>
        <el-table-column label="真实姓名" prop="accountName" align="center">
          <template slot-scope="scope">
            <p>{{ scope.row.accountName }}</p>
          </template>
        </el-table-column>
        <el-table-column label="身份证号码" prop="idCard" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.idCard }}</span>
          </template>
        </el-table-column>
        <el-table-column label="开户行" prop="bank" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.bank }}{{ scope.row.bankSub }}</span>
          </template>
        </el-table-column>
        <el-table-column label="银行卡号" prop="bankAccount" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.bankAccount }}</span>
          </template>
        </el-table-column>
        <el-table-column label="地区" prop="address" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.province }}{{ scope.row.city }}{{ scope.row.area }}</span>
          </template>
        </el-table-column>
        <el-table-column label="申请时间" align="center" prop="addTime">
          <template slot-scope="scope">
            <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" prop="status" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status == 1 ? 'success' : 'danger' ">{{ scope.row.status | statusFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="拒绝原因" align="center" prop="remark">
          <template slot-scope="scope">
            <span>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" align="center">
          <template slot-scope="scope">
            <el-button-group>
              <el-link v-if="scope.row.status == 0" type="primary" @click="handleVerifyBank(scope.row)">审核</el-link>
              <el-link type="primary" @click="handleUpdateBank(scope.row)">编辑</el-link>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>

    <!--审核框-->
    <el-dialog title="审核" :visible.sync="dialogBankVisible">
      <el-form ref="bankForm" :rules="bankRules" :model="verifyForm" label-width="120px" label-position="right">
        <el-form-item label="审核结果" prop="status">
          <el-radio-group v-model="verifyForm.status">
            <el-radio :label="1" border>通过</el-radio>
            <el-radio :label="2" border>不通过</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider>拒绝原因</el-divider>
        <el-form-item label="拒绝原因" prop="reasonCheck">
          <el-select v-model="verifyForm.reasonCheck" placeholder="请选择" style="width: 100%;" clearable @change="$set(verifyForm,'remark',verifyForm.reasonCheck);">
            <el-option
              key="0"
              value="此卡被没收,请于发卡方联系"
              label="此卡被没收,请于发卡方联系"
            />
            <el-option
              key="1"
              value="持卡人认证失败"
              label="持卡人认证失败"
            />
            <el-option
              key="2"
              value="无效卡号"
              label="无效卡号"
            />
            <el-option
              key="3"
              value="此卡无对应发卡方"
              label="此卡无对应发卡方"
            />
            <el-option
              key="4"
              value="该卡未初始化或睡眠卡"
              label="该卡未初始化或睡眠卡"
            />
            <el-option
              key="5"
              value="作弊卡,吞卡"
              label="作弊卡,吞卡"
            />
            <el-option
              key="6"
              value="发卡方不支持的交易"
              label="发卡方不支持的交易"
            />
            <el-option
              key="7"
              value="此卡已经挂失"
              label="此卡已经挂失"
            />
            <el-option
              key="8"
              value="持卡人信息有误"
              label="持卡人信息有误"
            />
            <el-option
              key="9"
              value="银行卡号码有误"
              label="银行卡号码有误"
            />
            <el-option
              key="10"
              value="其他"
              label="其他"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="填写原因" prop="remark">
          <el-input v-model="verifyForm.remark" type="textarea" placeholder="请输入拒绝理由" :rows="6" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogBankVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmBank">确定</el-button>
      </div>
    </el-dialog>

    <!--编辑框-->
    <el-dialog title="编辑" :visible.sync="dialogEditBankVisible">
      <el-form ref="editBankForm" :rules="bankRules" :model="editBankForm" label-width="120px" label-position="right" style="width: 400px; margin-left:50px;">
        <el-form-item label="真实姓名" prop="accountName">
          <el-input v-model="editBankForm.accountName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="身份证" prop="idCard">
          <el-input v-model="editBankForm.idCard" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item prop="pca" label="开户地址">
          <el-cascader
            v-model="editBankForm.pca"
            size="small"
            style="width:100%;"
            :options="addressOptions"
            clearable
          />
        </el-form-item>
        <el-form-item label="开户银行" prop="bank">
          <el-select v-model="editBankForm.bank" placeholder="银行列表" filterable clearable class="filter-item">
            <el-option v-for="item in bankOptions" :key="item.key" :label="item.text" :value="item.text" />
          </el-select>
        </el-form-item>
        <el-form-item label="支行名称" prop="bankSub">
          <el-input v-model="editBankForm.bankSub" placeholder="请输入支行名称" />
        </el-form-item>
        <el-form-item label="银行卡号" prop="bankAccount">
          <el-input v-model="editBankForm.bankAccount" placeholder="请输入银行卡号" />
        </el-form-item>
        <el-form-item label="审核" prop="status">
          <el-radio-group v-model="editBankForm.status">
            <el-radio :label="1">通过</el-radio>
            <el-radio :label="2">不通过</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="editBankForm.remark" type="textarea" placeholder="请输入拒绝理由" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogEditBankVisible=false">取消</el-button>
        <el-button type="primary" @click="editSubmitBank">确定</el-button>
      </div>
    </el-dialog>

    <br>
    <br>
    <el-divider><h3>充值记录(展示最近5条记录)</h3></el-divider>
    <el-table
      ref="rechargeTable"
      :key="rechargeTableKey"
      v-loading="rechargeListLoading"
      :data="rechargeList"
      border
      fit
      size="mini"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="商家充值记录" prop="" align="center">
        <template slot="header" slot-scope="scope">
          充值记录
          <router-link :to="'/fund/recharge?memberName='+memberDetail.code">
            <span class="link-type">查看更多</span>
          </router-link>
        </template>
        <el-table-column label="订单号" prop="orderNo" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.orderNo }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户名" align="center" prop="realname">
          <template slot-scope="scope">
            <span>{{ scope.row.memberName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="支付方式" align="center" prop="paymethod">
          <template slot-scope="scope">
            <span>{{ scope.row.paymethod | rechargeTypeFilter }}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column label="付款截图" width="150" align="center" prop="img">
         <template slot-scope="scope">
           <el-image
              style="width: 100px; "
              :src="scope.row.img"
              :preview-src-list="[scope.row.img]"
             >
             </el-image>
         </template>
       </el-table-column> -->
        <el-table-column label="充值金额" align="center" prop="amount">
          <template slot-scope="scope">
            <p style="color:red;">{{ scope.row.amount | moneyFormat }}</p>
          </template>
        </el-table-column>
        <el-table-column label="卡号后四位" align="center" prop="remitAccount">
          <template slot-scope="scope">
            <span>卡号后四位: <span style="color:red;">{{ scope.row.remitAccount }}</span></span>
          </template>
        </el-table-column>
        <el-table-column label="打款人" align="center" prop="remitRealname">
          <template slot-scope="scope">
            <span>{{ scope.row.remitRealname }}</span>
          </template>
        </el-table-column>
        <el-table-column label="打款银行" align="center" prop="remitBank">
          <template slot-scope="scope">
            <span>{{ scope.row.remitBank }}</span>
          </template>
        </el-table-column>
        <el-table-column label="充值时间" align="center" prop="addTime">
          <template slot-scope="scope">
            <span v-if="scope.row.addTime">{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status == '0'" type="danger">{{ scope.row.status | rechargeStatusFilter }}</el-tag>
            <el-tag v-if="scope.row.status == '1'" type="success">{{ scope.row.status | rechargeStatusFilter }}</el-tag>
            <el-tag v-if="scope.row.status == '2'" type="danger">{{ scope.row.status | rechargeStatusFilter }}</el-tag>
            <el-tag v-if="scope.row.status == '3'" type="info">{{ scope.row.status | rechargeStatusFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="备注" prop="remark">
          <template slot-scope="scope">
            <span>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right">
         <template slot-scope="scope">
           <el-button v-if="scope.row.status == '0'" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">审核</el-button>
         </template>
       </el-table-column> -->
      </el-table-column>
    </el-table>

  </div>
</template>
<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
  .tips{
    font-size: 12px;
    color:#999;
    margin-left: 5px;
  }
</style>

<script>
import { getMember, updateMember, updateMemberAccount, getMemberGroupSelector, getMemberDetail, getMemberBankList, verifyMemberBank, updateMemberBank, deleteMemberBank, addSystemBlock, removeSystemBlock, updateInvite, transferAmount, sendMsg, limitWithdraw } from '@/api/member'
import { getWithdrawList, countWithdraw, verifyWithdraw, setDoingWithdraw, getRechargeLog, verifyRecharge, getLastWithdraw, applyWithdraw } from '@/api/fund'
import { getConfigMissionSelector, getSecretSelector, getShopCateSelector } from '@/api/config'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'
import {
  provinceAndCityDataPlus, regionData, CodeToText
} from 'element-china-area-data'

const statusOptions = [
  { key: '0', text: '待审核' },
  { key: '1', text: '通过' },
  { key: '2', text: '不通过' },
  { key: '3', text: '处理中' }
]

const typeOptions = [
  { key: 1, text: '银行' },
  { key: 2, text: '支付宝' },
  { key: 3, text: '微信' }
]
// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const rechargeStatusOptions = [
  { key: '0', text: '待审核' },
  { key: '1', text: '通过' },
  { key: '2', text: '不通过' },
  { key: '3', text: '已取消' }
]

const rechargeTypeOptions = [
  { key: 1, text: '支付宝' },
  { key: 2, text: '微信' },
  { key: 3, text: '财付通' },
  { key: 4, text: '网银' }
]
// arr to obj ,such as { CN : "China", US : "USA" }
const rechargeStatusKeyValue = rechargeStatusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const rechargeTypeKeyValue = rechargeTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const buyerStatusOptions = [
  { key: 0, text: '待审核' },
  { key: 1, text: '已通过' },
  { key: 2, text: '已驳回' },
  { key: 3, text: '已锁定' },
  { key: 4, text: '已停用' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const buyerStatusKeyValue = buyerStatusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const ageOptions = [
  { key: 1, text: '20岁以下' },
  { key: 2, text: '21-25岁' },
  { key: 3, text: '26-32岁' },
  { key: 4, text: '33-40岁' },
  { key: 5, text: '41岁以上' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const ageKeyValue = ageOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const memberStatusOptions = [
  { key: '1', text: '正常' },
  { key: '-1', text: '停用' },
  { key: '-2', text: '注销' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const memberStatusKeyValue = memberStatusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const bankOptions = [
  { key: 'ICBC', text: '中国工商银行' },
  { key: 'ABC', text: '中国农业银行' },
  { key: 'BOC', text: '中国银行' },
  { key: 'CCB', text: '中国建设银行' },
  { key: 'CIB', text: '兴业银行' },
  { key: 'SPDB', text: '浦东发展银行' },
  { key: 'BCM', text: '交通银行' },
  { key: 'ECITIC', text: '中信银行' },
  { key: 'CEB', text: '中国光大银行' },
  { key: 'CMB', text: '招商银行' },
  { key: 'GDB', text: '广发银行' },
  { key: 'PSBC', text: '中国邮政储蓄银行' },
  { key: 'PAB', text: '平安银行' },
  { key: 'CMSB', text: '民生银行' }
]

const bankKeyValue = bankOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const orderLimitOptions = [
  { key: 0, text: '无限制' },
  { key: 1, text: '仅派浏览' },
  { key: 2, text: '仅派垫付单' },
  { key: 3, text: '垫付限金额' },
  { key: 4, text: '禁止派单' }
]

const orderLimitKeyValue = orderLimitOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const publishLimitOptions = [
  { key: '0', text: '无限制' },
  { key: '1', text: '关闭浏览' },
  { key: '2', text: '关闭黑科技' },
  { key: '3', text: '关闭特别任务' },
  { key: '4', text: '关闭抖音淘宝' },
  { key: '5', text: '垫付限金额' },
  { key: '6', text: '禁止发单' }
]

const publishLimitKeyValue = publishLimitOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const taoAgeOptions = [
  { key: 1, text: '1年' },
  { key: 2, text: '2年' },
  { key: 3, text: '3年' },
  { key: 4, text: '3年以上' }
]

export default {
  name: 'MemberDetailInfo',
  directives: { waves },
  components: { Upload, ImageSource },
  filters: {
    statusFilter(status) {
      return statusKeyValue[status]
    },
    typeFilter(status) {
      return typeKeyValue[status]
    },
    rechargeStatusFilter(status) {
      return rechargeStatusKeyValue[status]
    },
    rechargeTypeFilter(status) {
      return rechargeTypeKeyValue[status]
    },
    buyerStatusFilter(status) {
      return buyerStatusKeyValue[status]
    },
    ageFilter(status) {
      return ageKeyValue[status]
    },
    memberStatusFilter(status) {
      return memberStatusKeyValue[status]
    },
    bankFilter(status) {
      return bankKeyValue[status]
    },
    orderLimitFilter(status) {
      return orderLimitKeyValue[status]
    },
    publishLimitFilter(status) {
      return publishLimitKeyValue[status]
    }
  },
  data() {
    return {
      memberId: null,
      addressOptions: regionData,
      searchParams: {},
      memberDetail: {},
      memberRealname: {},
      memberAccount: {},
      accountForm: {},
      currentActive: '1',
      groupOptions: [],
      missionGroupOptions: [],
      secretOptions: [],
      visible: false,
      withdrawTableKey: 0,
      withdrawList: null,
      withdrawTotal: 0,
      withdrawListLoading: false,
      withdrawListQuery: {
        page: 1,
        pageSize: 20
      },
      dialogWithdrawVisible: false,
      withdraw: {},
      withdrawRules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
      bankTableKey: 1,
      bankList: null,
      bankTotal: 0,
      bankListLoading: false,
      bankListQuery: {
        page: 1,
        pageSize: 20
      },
      rechargeTableKey: 2,
      rechargeList: null,
      rechargeTotal: 0,
      rechargeListLoading: false,
      rechargeListQuery: {
        page: 1,
        pageSize: 5
      },
      buyerTableKey: 3,
      buyerList: null,
      buyerTotal: 0,
      buyerListLoading: false,
      buyerListQuery: {
        page: 1,
        pageSize: 20
      },
      sellerTableKey: 4,
      sellerList: null,
      sellerTotal: 0,
      sellerListLoading: false,
      sellerListQuery: {
        page: 1,
        pageSize: 20
      },
      dialogBankVisible: false,
      dialogEditBankVisible: false,
      verifyForm: {},
      editBankForm: {},
      bankRules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
      bankOptions,
      dialogBuyerVisible: false,
      dialogEditBuyerVisible: false,
      editBuyerForm: {},
      buyerRules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
      shopCateOptions: [],
      ageOptions,
      dialogSameAddressVisible: false,
      areaCode: null,
      shopCateId: null,
      phone: null,
      dialogSellerVisible: false,
      dialogEditSellerVisible: false,
      dialogTransferSellerVisible: false,
      editSellerForm: {},
      sellerRules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
      dialogAccountVisible: false,
      dialogApplyWithdrawVisible: false,
      applyWithdrawRules: {
        accountName: [{ required: true, message: '收款姓名不能为空', trigger: 'blur' }],
        bank: [{ required: true, message: '提现银行不能为空', trigger: 'blur' }],
      	bankAccount: [{ required: true, message: '银行卡号不能为空', trigger: 'blur' }],
      	amount: [{ required: true, message: '提现金额不能为空', trigger: 'blur' }]
      },
      applyWithdraw: {},
      orderLimitOptions,
      dialogTransferVisible: false,
      transferRules: {
        uid: [{ required: true, message: '收款人ID不能为空', trigger: 'blur' }],
      	amount: [{ required: true, message: '转账金额不能为空', trigger: 'blur' }]
      },
      transferForm: {},
      dialogMsgVisible: false,
      msgRules: {
        title: [{ required: true, message: '标题不能为空', trigger: 'blur' }],
      	content: [{ required: true, message: '内容不能为空', trigger: 'blur' }]
      },
      msgForm: {},
      dialogFreezeVisible: false,
      freezeRules: {
        freezeReason: [{ required: true, message: '理由不能为空', trigger: 'blur' }]
      },
      freezeForm: {},
      publishLimitOptions,
      publishLimitArr: [],
      taoAgeOptions,
      platform: process.env.VUE_APP_TITLE
    }
  },
  created() {
    const uid = this.$route.query && this.$route.query.uid
    if (uid) {
      this.searchParams.uid = uid
    }
    const memberId = this.$route.query && this.$route.query.memberId
    if (memberId) {
      this.searchParams.memberId = memberId
    }
    this.getMember(true)
    this.getMemberGroupSelector()
    this.getConfigMissionSelector()
    this.getSecretSelector()
  },
  methods: {
    getMemberGroupSelector() {
      getMemberGroupSelector().then(response => {
        this.groupOptions = response.data
      })
    },
    getConfigMissionSelector() {
      getConfigMissionSelector().then(response => {
        this.missionGroupOptions = response.data
      })
    },
    getSecretSelector() {
      getSecretSelector().then(response => {
        this.secretOptions = response.data
      })
    },
    getMember(isAllRefresh) {
      getMemberDetail(this.searchParams).then(response => {
        const result = response.data
        if (result && result.member) {
          this.memberDetail = result.member
          this.searchParams.uid = this.memberDetail.uid
          this.memberId = this.memberDetail.id
          this.memberDetail.password = null
          this.memberDetail.payPassword = null
          this.memberRealname = result.realname
          if (this.memberDetail.publishLimit) {
            this.publishLimitArr = this.memberDetail.publishLimit.split(',')
          }
          if (!this.memberRealname) {
            this.memberRealname = {}
          }
          this.memberAccount = result.memberAccount
          if (isAllRefresh) {
            this.getWithdrawList(this.memberDetail.id)
            this.getBankList(this.memberDetail.id)
            this.getRechargeList(this.memberDetail.id)
          }
        }
      }).catch(err => {
        console.log(err)
      })
    },
    updateMemberInfo() {
      this.memberDetail.addTime = null
      this.memberDetail.updTime = null
      this.memberDetail.birthday = renderTime(this.memberDetail.birthday)
      updateMember(this.memberDetail).then(response => {
        this.$notify({
          title: '成功',
          message: '保存成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    updateMemberAccount() {
      this.accountForm.memberId = this.memberDetail.id
      updateMemberAccount(this.accountForm).then(response => {
        this.memberAccount = response.data
        this.$notify({
          title: '成功',
          message: '保存成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    getWithdrawList(memberId) {
      this.withdrawListLoading = true
    	this.withdrawListQuery.memberId = memberId
      getWithdrawList(this.withdrawListQuery).then(response => {
        this.withdrawList = response.data.list
        this.withdrawTotal = response.data.total
    		this.withdrawListLoading = false
      })
    },
    getBankList(memberId) {
      this.bankListLoading = true
      this.bankListQuery.memberId = memberId
      getMemberBankList(this.bankListQuery).then(response => {
        this.bankList = response.data.list
        this.bankTotal = response.data.total
        this.bankListLoading = false
      })
    },
    getRechargeList(memberId) {
      this.rechargeListLoading = true
      this.rechargeListQuery.memberId = memberId
      getRechargeLog(this.rechargeListQuery).then(response => {
        this.rechargeList = response.data.list
        this.rechargeTotal = response.data.total
    		this.rechargeListLoading = false
      })
    },
    setDoingWithdraw(row) {
    	const id = row.id
      const formData = {
      	ids: id
      }
      setDoingWithdraw(formData).then((response) => {
      	this.getWithdrawList(this.memberId)
      	if (response.description && response.description != '') {
          const message = response.description.replaceAll('|', '\n')
      		this.$notify({
      		  title: '失败',
      		  message: message,
      		  type: 'error',
      		  duration: 2000
      		})
      	}
        this.$notify({
          title: '成功',
          message: '处理成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    handleVerifyWithdraw(row) {
      this.withdraw = { status: '1', ids: row.id }// copy obj
      this.dialogWithdrawVisible = true
      this.$nextTick(() => {
        this.$refs['withdrawForm'].clearValidate()
      })
    },
    confirmWithdraw() {
    	this.$refs['withdrawForm'].validate((valid) => {
    	  if (valid) {
    	    const formData = {
    				ids: this.withdraw.ids,
    				status: this.withdraw.status,
    				remark: this.withdraw.remark
    			}
    	    verifyWithdraw(formData).then((response) => {
    				this.getWithdrawList(this.memberId)
    	      this.dialogWithdrawVisible = false
    				if (response.description && response.description != '') {
              const message = response.description.replaceAll('|', '\n')
    					this.$notify({
    					  title: '失败',
    					  message: message,
    					  type: 'error',
    					  duration: 2000
    					})
              return
    				}
    	      this.$notify({
    	        title: '成功',
    	        message: '审核提现成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    handleVerifyBank(row) {
      this.verifyForm = { status: 1, ids: row.id }// copy obj
      this.dialogBankVisible = true
      this.$nextTick(() => {
        this.$refs['bankForm'].clearValidate()
      })
    },
    confirmBank() {
    	this.$refs['bankForm'].validate((valid) => {
    	  if (valid) {
    	    const formData = {
    				ids: this.verifyForm.ids,
    				status: this.verifyForm.status,
    				remark: this.verifyForm.remark
    			}
    	    verifyMemberBank(formData).then((response) => {
    				this.getBankList(this.memberId)
    	      this.dialogBankVisible = false
    				if (response.description && response.description != '') {
    					this.$notify({
    					  title: '失败',
    					  message: response.description,
    					  type: 'error',
    					  duration: 2000
    					})
    				}
    	      this.$notify({
    	        title: '成功',
    	        message: '审核成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    handleUpdateBank(row) {
      this.dialogEditBankVisible = true
      this.editBankForm = Object.assign({}, row) // copy obj
      this.editBankForm.pca = [this.editBankForm.provinceCode, this.editBankForm.cityCode, this.editBankForm.areaCode]
      this.$nextTick(() => {
        this.$refs['editBankForm'].clearValidate()
      })
    },
    editSubmitBank() {
    	this.$refs['editBankForm'].validate((valid) => {
    	  if (valid) {
    	    const formData = Object.assign({}, this.editBankForm)
          if (this.editBankForm.pca && this.editBankForm.pca.length > 0) {
         	formData.provinceCode = this.editBankForm.pca[0]
         	formData.cityCode = this.editBankForm.pca[1]
         	formData.areaCode = this.editBankForm.pca[2]
            formData.province = CodeToText[formData.provinceCode]
            formData.city = CodeToText[formData.cityCode]
            formData.area = CodeToText[formData.areaCode]
          }
    			formData.addTime = null
    			formData.updTime = null
    	    updateMemberBank(formData).then(() => {
    				this.getBankList(this.memberId)
    	      this.dialogEditBankVisible = false
    	      this.$notify({
    	        title: '成功',
    	        message: '保存成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    updateMemberInfo() {
      this.memberDetail.addTime = null
      this.memberDetail.updTime = null
      this.memberDetail.birthday = renderTime(this.memberDetail.birthday)
      updateMember(this.memberDetail).then(response => {
        this.$notify({
          title: '成功',
          message: '保存成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    updateMemberAccount() {
      this.accountForm.memberId = this.memberDetail.id
      updateMemberAccount(this.accountForm).then(response => {
        this.getMember(false)
        this.dialogAccountVisible = false
        this.$notify({
          title: '成功',
          message: '保存成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    initPassword() {
      this.updateMember({ id: this.memberId, password: '123456' }, '重置成功')
    },
    updateMember(form, msg) {
      updateMember(form).then(response => {
        this.$notify({
          title: '成功',
          message: msg,
          type: 'success',
          duration: 2000
        })

        this.getMember(false)
      })
    },
    handleAccount() {
      this.accountForm = {}
      this.dialogAccountVisible = true
      this.$nextTick(() => {
        this.$refs['accountForm'].clearValidate()
      })
    },
    updateInvite() {
      updateInvite({ memberId: this.memberId, inviteUid: this.memberDetail.tjrUid }).then(response => {
        this.$notify({
          title: '成功',
          message: '修改成功',
          type: 'success',
          duration: 2000
        })
        this.getMember(false)
      })
    },
    handleApplyWithdraw() {
      this.applyWithdraw = {}
      getLastWithdraw({ type: 1, memberId: this.memberId }).then((result) => {
      	 const response = result.data
        if (response) {
          this.applyWithdraw = {
            accountName: response.realname,
            bank: response.bank,
            bankAccount: response.bankAccount
          }
        }
      })
      this.dialogApplyWithdrawVisible = true
      this.$nextTick(() => {
        this.$refs['applyWithdrawForm'].clearValidate()
      })
    },
    confirmApplyWithdraw() {
    	this.$refs['applyWithdrawForm'].validate((valid) => {
    	  if (valid) {
    	    const formData = Object.assign({}, this.applyWithdraw)
          formData.memberId = this.memberId
    			formData.addTime = null
    			formData.updTime = null
    	    applyWithdraw(formData).then(() => {
    				this.getMember(true)
    	      this.dialogApplyWithdrawVisible = false
    	      this.$notify({
    	        title: '成功',
    	        message: '申请成功, 请等待平台审核打款',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    handleTransfer() {
      this.transferForm = {}
      this.dialogTransferVisible = true
      this.$nextTick(() => {
        this.$refs['transferForm'].clearValidate()
      })
    },
    confirmTransfer() {
    	this.$refs['transferForm'].validate((valid) => {
    	  if (valid) {
    	    const formData = Object.assign({}, this.transferForm)
          formData.memberId = this.memberId
    			formData.addTime = null
    			formData.updTime = null
    	    transferAmount(formData).then(() => {
    				this.getMember(true)
    	      this.dialogTransferVisible = false
    	      this.$notify({
    	        title: '成功',
    	        message: '转账成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    handleSendMsg() {
      this.msgForm = {}
      this.dialogMsgVisible = true
      this.$nextTick(() => {
        this.$refs['msgForm'].clearValidate()
      })
    },
    confirmSendMsg() {
    	this.$refs['msgForm'].validate((valid) => {
    	  if (valid) {
    	    const formData = Object.assign({}, this.msgForm)
          formData.memberId = this.memberId
    			formData.addTime = null
    			formData.updTime = null
    	    sendMsg(formData).then(() => {
    				this.getMember(true)
    	      this.dialogMsgVisible = false
    	      this.$notify({
    	        title: '成功',
    	        message: '发送成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    limitWithdraw(form, msg) {
      limitWithdraw(form).then(response => {
        this.$notify({
          title: '成功',
          message: msg,
          type: 'success',
          duration: 2000
        })

        this.getMember(false)
      })
    }

  }
}
</script>

<style scoped="scoped">
.wrap-info-table div {
	word-break: break-all;
  font-size: 12px;
}
.h-50 {
	height: 50px;
}
.w-120 {
	width: 120px;
	text-align: center;
}
.bg-color-4 {
	background-color: #F5F7FA;
}
.b-color-4 {
	border: 1px solid #e8ecef;
}
.border-l {
	border-left: 1px solid #e8ecef;
}
.border-r {
	border-right: 1px solid #e8ecef;
}
.border-t {
	border-top: 1px solid #e8ecef;
}
.border-b {
	border-bottom: 1px solid #e8ecef;
}
</style>
