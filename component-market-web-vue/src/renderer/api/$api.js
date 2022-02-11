import axios from 'axios'
import {message} from 'ant-design-vue'

const baseURL = 'http://127.0.0.1:9999'

const $api = axios.create({
  baseURL: baseURL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  }
})
$api.baseURL = baseURL

$api.interceptors.response.use((response) => {
  // 获取接口返回结果
  const res = response.data
  if (res != null) {
    // code为0，直接把结果返回回去，这样前端代码就不用在获取一次data.
    if (res.success) {
      return res.data
    } else {
      // 错误显示可在service中控制，因为某些场景我们不想要展示错误
      // Message.error(res.message);
      message.error(res.message)
      return Promise.reject(res)
    }
  } else {
    return Promise.reject(response)
  }
}, () => {
  message.info('网络请求异常，请稍后重试!')
})

export default $api
