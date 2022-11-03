module.exports = {
  root: true,
  env: {
    node: true,
  },
  plugins: ["import"],


  extends: ["plugin:vue/essential", "eslint:recommended", "@vue/prettier"],
  parserOptions: {
    parser: "babel-eslint",
  },
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "warn" : "off",
    "import/no-extraneous-dependencies": [
      "error", {
         "devDependencies": false, 
         "optionalDependencies": false, 
         "peerDependencies": false, 
         "packageDir": "./"
      }
    ]  
  },
};
