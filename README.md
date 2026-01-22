# 学生管理系统 - 设计文档

## 1. 项目概述

### 1.1 系统简介

此学生管理系统是一个简单的 Java 控制台应用程序，用于管理学生、教师和课程信息。

### 1.2 核心功能

- 学生和教师信息的增删改查
- 课程容量控制与并发选课
- 教师成绩录入
- 成绩统计分析
- 日志记录

## 2. 系统架构

### 2.1 包结构

```text
io.github.crispyxyz.studentmanagement
├── model/      (数据模型)
├── exception/  (自定义异常)
├── util/       (工具类)
├── (主程序类)
└── (测试类)
```

## 3. 核心类设计

### 3.1 数据模型

#### 3.1.1 `Person` 抽象类

封装人员基础信息。所有属性通过 `ValidatingUtil` 验证。

属性：
- `id`: 人员 ID
- `name`: 姓名
- `age`: 年龄

#### 3.1.2 `Student` 学生类

继承自 `Person`，实现 `Details` 接口。

属性：
- `major`: 专业
- `scores`: 课程名与成绩的映射表

方法：
- `showDetails`: 展示学生详细信息

#### 3.1.3 `Teacher` 教师类

继承自 `Person`，实现 `Details` 接口。

属性：
- `courses`: 授课列表

方法：
- `recordScore`: 录入学生成绩
- `showDetails`: 展示教师详细信息

#### 3.1.4 `Course` 课程类

属性：
- `id`: 课程 ID
- `name`: 课程名称
- `capacity`: 课程容量
- `remain`: 剩余名额
- `students`: 选课学生列表

方法：
- `enroll`: 线程安全的选课方法

#### 3.1.5 `Detail` 接口

方法：
- `showDetails`: 展示详细信息

### 3.2 业务逻辑

#### 3.2.1 `GenericDataManager` 泛型数据管理类

对 `Person` 子类进行 CRUD 操作。

方法：
- `add`: 添加
- `deleteById`: 根据 ID 删除
- `update`: 根据 ID 更新
- `findById`: 根据 ID 查询

#### 3.2.2 `ScoreManager` 成绩管理类

成绩相关统计分析。

方法：
- `getFailedStudent`: 统计不及格学生
- `getCourseStat`: 获取课程统计信息

内部类：
- `CourseStat`: 封装课程统计数据

#### 3.2.3 `EnrollThread` 选课线程

模拟并发选课。

### 3.3 工具类

#### 3.3.1 `ValidatingUtil` 验证工具类

用于验证参数。

方法：
- `isBlank`: 判断字符串是否是空白字符串
- `validateString`: 验证字符串非空
- `validateDouble`: 验证数值范围

#### 3.3.2 `LoggerProvider` 日志提供类

记录操作日志。

### 3.4 异常类

#### 3.4.1 `DataNotFoundException`

要查找、更新、删除的 ID 不存在时，抛出此异常。

#### 3.4.2 `InvalidCourseException`

教师录入未教授的科目的成绩时，抛出此异常。

