# exchange-wallet-java
This is an exchange wallet project implemented in Java。

## 项目主要分为三个大模块，加密服务、数据服务、业务服务
## 1. 加密服务：主要负责 密钥管理、地址生成、交易签名、签名验证。
密钥管理三原则
1. 生产环境禁止本地存储私钥
2. 签名操作需使用硬件加密机
3. 实施双人分权控制（M-of-N签名）

## 2. 数据服务：主要负责区块链数据的获取、解析、存储；提供区块、交易、地址余额等数据的查询。
## 3. 业务服务：主要实现交易所的功能，如 充值、提币、归集、用户账户管理等业务逻辑。
