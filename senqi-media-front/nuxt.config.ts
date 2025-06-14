// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    compatibilityDate: '2025-05-15',
    devtools: { enabled: true },
    modules: ['@ant-design-vue/nuxt'],

    // import main css
    css: ['~/assets/css/main.css'],

    // Ant design vue options
    antd: {}
})