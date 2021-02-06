// module.exports = {
//     devServer: {
//         public: 'http://192.168.1.199:8080/',
//         disableHostCheck: true,
//         headers: {
//             'Access-Control-Allow-Origin': '*',
//         },
//     }
// }

// module.exports = {
//     devServer: {
//         disableHostCheck: true,
//         port: 8080,
//         public: 'http://192.168.1.199:8080/'
//     },
//     publicPath: "/",
//     // baseUrl: "/",
//     chainWebpack: config => {
//         config
//             .plugin("html")
//             .tap(args => {
//                 // if (process.env.DEMO) {
//                     args[0].template = "src/main/webapp/index.html"
//                 // }
//                 return args
//             })
//     }
// }