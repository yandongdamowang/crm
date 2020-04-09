<template>
  <div class="wrapper">
    <div class="left-pic">
      <!-- <div class="left-pic" /> -->
    </div>

    <div class="right">
      <div class="title">{{ name }}</div>
      <el-tabs
        v-model="activeName"
        class="table"
        @tab-click="handleClick"
      >

        <el-tab-pane
          label="账号密码登录"
          name="first"
        >
          <el-form
            ref="loginForm"
            :model="loginForm"
            :rules="loginRules"
            class="login-form"
            auto-complete="on"
            label-position="left"
          >

            <el-form-item prop="username">
              <div class="content">用户名</div>
              <el-input
                ref="name"
                v-model="loginForm.username"
                autofocus="autofocus"
                name="username"
                type="text"
                auto-complete="on"
                placeholder="请输入用户名"
                @keyup.enter.native="handleLogin"
              />
            </el-form-item>

            <el-form-item prop="password">
              <div class="content">密码</div>
              <el-input
                v-model="loginForm.password"
                type="password"
                name="password"
                auto-complete="on"
                placeholder="请输入密码"
                @keyup.enter.native="handleLogin"
              />
            </el-form-item>

            <el-form-item>
              <el-button
                :loading="loading"
                class="submit-btn"
                @click.native.prevent="handleLogin"
              >
                登录
              </el-button>
            </el-form-item>

          </el-form>
        </el-tab-pane>

        <el-tab-pane
          label="手机号登录"
          name="second"
        >
          <el-form
            ref="loginForm"
            :model="loginForm"
            :rules="loginRules"
            class="login-form"
            auto-complete="on"
            label-position="left"
          >

            <el-form-item prop="username">
              <div class="content">手机号</div>
              <el-input
                ref="name"
                v-model="loginForm.username"
                autofocus="autofocus"
                name="username"
                type="text"
                auto-complete="on"
                placeholder="请输入手机号"
                @keyup.enter.native="handleLogin"
              />
            </el-form-item>

            <el-form-item prop="password">
              <div class="content">验证码</div>

              <div class="code">
                <el-input
                  v-model="loginForm.password"
                  type="password"
                  name="password"
                  auto-complete="on"
                  placeholder="请输入验证码"
                  @keyup.enter.native="handleLogin"
                />

                <el-button
                  v-show="sendAuthCode"
                  plain
                  @click="getAuthCode"
                >获取验证码</el-button>
                <el-button
                  v-show="!sendAuthCode"
                  plain
                >重新发送 {{ auth_time }} 秒</el-button>
              </div>

            </el-form-item>

            <el-form-item>
              <el-button
                :loading="loading"
                class="submit-btn"
                @click.native.prevent="handleLogin"
              >
                登录
              </el-button>
            </el-form-item>

          </el-form>

        </el-tab-pane>
        <div class="regist">
          <el-button type="text">免费注册</el-button>
          <el-button type="text">忘记密码?</el-button>
        </div>
      </el-tabs>

    </div>

    <img
      :src="logo"
      class="logo"
    >
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (value.length == 0) {
        callback(new Error('请输入账号'))
      } else {
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      activeName: 'first',
      sendAuthCode: true,
      // 倒计时
      auth_time: 0,
      loginForm: {
        username: '',
        password: '',
        telephone: '',
        code: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }]
      },
      loading: false,
      redirect: undefined,
      remember: false
    }
  },
  computed: {
    ...mapGetters(['logo', 'name'])
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  mounted() { },
  methods: {
    getAuthCode: function() {
      //   const verification = this.loginForm.telephone

      //   const url = ' '
      //   console.log('url', url)
      //   this.$http.get(url).then(
      //     function (response) {
      //       console.log('请求成功', response)
      //     },
      //     function (error) {
      //       console.log('请求失败', error)
      //     }
      //   )
      this.sendAuthCode = false
      // 设置倒计时秒
      this.auth_time = 30
      var auth_timetimer = setInterval(() => {
        this.auth_time--
        if (this.auth_time <= 0) {
          this.sendAuthCode = true
          clearInterval(auth_timetimer)
        }
      }, 1000)
    },

    handleClick(tab, event) {
      console.log(tab, event)
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store
            .dispatch('Login', this.loginForm)
            .then(res => {
              this.loading = false
              this.$router.push({ path: this.redirect || '/' })
            })
            .catch(() => {
              this.loading = false
            })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
$dark_gray: #ccc;
$light_gray: #333;
$login_theme: #00aaee;

/deep/ input {
  border: 0 none;
  background-color: white;
  -webkit-appearance: none;
  &:-webkit-autofill {
    background-image: none;
    -webkit-box-shadow: 0 0 0 1000px white inset !important;
    -webkit-text-fill-color: $light_gray !important;
  }
}
/deep/ .el-input__inner {
  height: 40px;
  padding: 0 12px;
  background-color: white;
  border: 0 none;
  border-bottom: 1px solid #e6e6e6 !important;
}
/deep/ .el-form-item__error {
  left: 12px;
}

/deep/ .el-tabs__header {
  padding: 0;
  position: relative;
  margin: 0 0 30px;
}

/deep/ .el-tabs__nav-wrap::after {
  background-color: #fff;
}

/deep/ .el-tabs__nav-scroll {
  margin-left: 60px;
}

.content {
  font-size: 16px;
  font-family: Microsoft YaHei;

  font-weight: 400;
  color: rgba(0, 0, 0, 0.65);
}
.code {
  display: flex;
  //   background: red;
}

.regist {
  text-align: justify;
  text-align-last: justify;
  //   margin: 0 0 30px 0px;
}

.wrapper {
  position: relative;
  width: 100%;
  display: flex;
  .left-pic {
    width: 100%;
    height: 100%;
    background: url("../../assets/img/login/login.png") no-repeat center;
    background-size: cover;
  }
  .right {
    position: absolute;
    width: 400px;
    background-color: #fff;
    display: flex;
    align-items: center;
    flex-direction: column;
    border-radius: 4px;
    right: 200px;
    top: 150px;
    height: 530px;
    .table {
      width: 350px;
      margin: 50px 0 0 0;
      height: 250px;
      font-size: 16px;
    }
    .title {
      color: $light_gray;
      margin: 25px 0 0 -180px;
      font-size: 24px;
      font-family: Microsoft YaHei;
      font-weight: 500;
      // text-align: center;
    }
    .el-form {
      //   width: 70%;
    }
    .submit-btn {
      width: 100%;
      line-height: 2;
      font-size: 16px;
      color: white;
      border-radius: 3px;
      background-color: $login_theme;
      display: block;
      margin: 30px 0 0 0px;
    }
    .el-button {
      border: 0 none;
    }
    .action-control {
      color: #999;
      /deep/ .el-checkbox {
        .el-checkbox__label {
          color: #999;
        }
        .el-checkbox__input.is-checked .el-checkbox__inner {
          background-color: $login_theme;
          border-color: $login_theme;
        }
      }

      .forget {
        cursor: pointer;
        float: right;
      }
    }
  }

  .register {
    width: 70%;
    padding-top: 30px;
    color: $light_gray;
    border-top: 1px solid #e6e6e6;
    text-align: center;
    margin-top: 28px;
    .register-btn {
      color: $login_theme;
      cursor: pointer;
    }
  }

  .logo {
    position: absolute;
    left: 60px;
    top: 50px;
    width: 180px;
    height: 48px;
    z-index: 200;
  }
}
</style>
