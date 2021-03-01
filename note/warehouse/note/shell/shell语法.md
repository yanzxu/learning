### 单引号和双引号区别

#### `test.sh`
```shell script
#!/bin/bash
do_date=$1
echo '$do_date'
echo "$do_date"
echo "'$do_date'"
echo '"$do_date"'
echo `date`
```

#### 执行 `test.sh 2020-06-14`
```text
$do_date
2020-06-14
'2020-06-14'
"$do_date"
2020 年 06 月 18 日 星期四 21:02:08 CST
```

#### 总结
```text
1. 单引号不取变量值；
2. 双引号取变量值；
3. 反引号`，执行引号中命令；
4. 双引号内部嵌套单引号，取出变量值；
5. 单引号内部嵌套双引号，不取出变量值；
```