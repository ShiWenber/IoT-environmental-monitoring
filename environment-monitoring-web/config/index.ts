// config/index.js
export default {
  development: {
    baseUrl: '/api', // 开发代理地址
    // assetsSubDirectory: 'static',
    // assetsPublicPath: '/',
    // proxyTable: {
    //   '/api': {
    //     target: 'http://boer.ink:5004', // 凡是遇到 /api 路径的请求，都映射到 target 属性
    //     changeOrigin: true,
    //     rewrite: path => path.replace(/^\/api/, '') // 重写 api 为 空，就是去掉它
    //   }
    // }
  },
  beta: {
    baseUrl: '//' // 测试接口域名
  },
  release: {
    baseUrl: '/api', // 正式接口域名
    // proxyTable: {
    //   '/api': {
    //     target: 'http://boer.ink:5004', // 凡是遇到 /api 路径的请求，都映射到 target 属性
    //     changeOrigin: true,
    //     pathRewrite: {
    //       '^/api': 'http://boer.ink:5004'
    //     }
    //   }
    // }
  }
}