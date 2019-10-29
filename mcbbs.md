# JulyGuild —— 一款强大的公会插件

## 为什么使用JulyGuild？

* 接近100%的功能交互GUI支持。
* 接近100%的可自定义的语言文件。
* 支持 `1.8 - 1.14.4` 主流版本。

## 功能列表

### 创建公会

支持3种方式创建公会：

* 金币
* 点券
* 物品

![](/Users/user/Desktop/july_ss/JulyGuild%20%E2%80%94%E2%80%94%20%E4%B8%80%E6%AC%BE%E5%BC%BA%E5%A4%A7%E7%9A%84%E5%85%AC%E4%BC%9A%E6%8F%92%E4%BB%B6.assets/006y8mN6gy1g8a7waludhj31bg0rw7a8.jpg)

#### 金币

使用 `Vault` 作为前置。

#### 点券

使用 `PlayerPoints` 作为前置。

#### 物品

```
    cost:
      money:
        amount: 1000
        enabled: true
      points:
        amount: 100
        enabled: true
      item:
        enabled: true
        amount: 1
        key_lore: '&a创建宗门'
```

以上面的配置文件为例，只需要把1个含 `§a创建宗门` lore的物品放在背包并点击按钮即可创建公会。

### 加入公会

玩家可以通过 `/guild main` 指令打开主界面，切换到公会列表后，点击公会图标即可申请加入。

### 公会权限

在公会中，有以下权限：

* 踢出成员。
* 任命管理员。
* 取消管理员。
* 玩家审批。
* 全员集结令。
* 购买公会图标
* 设置公会图标。

会长默认有最高权限，会长可以通过 GUI 为任意成员设置权限。

### 公会主城

可以为公会设置一个公共传送点，成员可以通过 GUI 来传送到公共传送点。

### 公会图标



### 公会信息

### 全员集结令

## 下载

链接：https://pan.baidu.com/s/1PsFgGwSyrBZ8UDAfCR2cXw 提取码：ll6n

**插件反馈交流群：786184610**

## 更新日志
