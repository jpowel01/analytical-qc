module.exports = {
  root: true,
  env: {
    node: true,
  },
  plugins: ["import", "prettier"],


  extends: ["plugin:vue/essential", "eslint:recommended", "@vue/prettier", "prettier"],
  parserOptions: {
    parser: "babel-eslint",
  },
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "warn" : "off",
    "no-unused-vars": "off",
    "no-unused-components": "off",
    "import/no-extraneous-dependencies": [
      "error", {
         "devDependencies": false, 
         "optionalDependencies": false, 
         "peerDependencies": false, 
         "packageDir": "./"
      }
    ],
    'prettier/prettier': [
      "warn",
      {
        "endOfLine": "auto"
      }
    ]
  
  },
};
