#### include标签

- 作用：
  - 通过include标签将sql片段与原sql片段拼接成一个完成的sql语句来执行

```xml
<sql id="sqlid">
    res_type_id,res_type
</sql>

<select id="selectbyId" resultType="com.property.vo.PubResTypeVO">
    select 
    <include refid="sqlid"/>
    from pub_res_type
</select>
```

- 引用通过一个xml中的标签

  ```xml
  <include refid="sqlid"/>
  ```

- 引用公用的sql片段

  ```xml
  <include refid="namespace.sqlid"/>
  ```

- include标签也可以用property标签自定义属性，在sql标签中通过${}取出对应的属性值

  ```xml
  <select id="queryPubResType" parameterType="com.property.vo.PubResTypeVO" resultMap="PubResTypeList">
      select  a.res_type_id,
      <include refid="com.common.dao.FunctionDao.SF_GET_LNG_RES_TYPE">
          <property name="AI_RES_TYPE_ID" value="a.res_type_id"/>
          <property name="lng" value="#{lngId}"/>
          <property name="female" value="'女'"/>
      </include> as res_type
      from    pub_res_type a
  </select>
  ```

  >resultMap: 适合使用返回值是自定义实体类的情况
  >
  >resultType: 适合使用返回值得到数据类型是非自定义的情况，即jdk提供的类型

  

