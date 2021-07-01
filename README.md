方法名规则   
xxx代表对象    
一、UIService层   
1.查询分页数据   
queryPageUI   
2.查询单条数据   
queryXXXUI   
3.新增数据   
addXXXUI   
4.修改数据   
updateXXXUI   
5.逻辑删除   
deleteXXXByLogicUI   
6.物理删除   
deleteXXXUI   

二、Service层   
1.查询分页数据   
queryPage   
2.查询单条数据   
queryXXX   
3.新增数据   
addXXX   
4.修改数据   
updateXXX   
5.逻辑删除   
deleteXXXByLogic   
6.物理删除   
deleteXXX   

三、DAO层
继承BaseMapper<XXX>


// TODO
删除方法，通过配置选项来确定是逻辑删除还是物理删除