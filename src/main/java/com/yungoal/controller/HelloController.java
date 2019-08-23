package com.yungoal.controller;

import com.yungoal.constant.ConstantSetting;
import com.yungoal.domain.TestEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    /*
     * 在ApplicationServer中使用了PropertySource配置了properties源后，从相应的源如resource.properties中加载值。
     * */
    @Value("${person.name}")
    private String name;

    /*
     *  Autowired区别于Resource的不同处在于，Autowired是按类别导入，如果想指定名称，可以配合Qualifier使用。
     * */
    // @Autowired
    // @Qualifier("constantSetting")
    @Resource
    private ConstantSetting constantSetting;

    /*
     * 最简单的使用
     * */
    @RequestMapping("/hello")
    public String hello() {
        return "hello " + name + " !" + " from company " + constantSetting.getCompany();
    }

    /*
     * 系统自动直接将实体序列化为JSON返回。
     * */
    @RequestMapping("/constant")
    @ResponseBody //相当于把结果JSON处理后返回。
    public ConstantSetting constant() {
        return constantSetting;
    }

    /*
     * 当使用Post请求，Content-Type=application/json调用测试： {"id":1,"name":"Sindrol"}
     * */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public TestEntry constant(@RequestBody TestEntry test) {
        return test;
    }

    /*
     * 当使用Get请求
     * */
    @GetMapping(value = "/test")
    @ResponseBody
    public TestEntry constant(@RequestParam int id, @RequestParam String name) {
        TestEntry test = new TestEntry();
        test.setId(id);
        test.setName(name);
        return test;
    }

    /*
     * 当使用Get请求，并设置响应状态
     * */
    @GetMapping(value = "/request")
    @ResponseBody
    public TestEntry request(HttpServletRequest request, HttpServletResponse response, @RequestParam int id, @RequestParam String name) {
        response.setStatus(500);
        TestEntry test = new TestEntry();
        test.setId(id);
        test.setName(name);
        return test;
    }

    /*
     * 当使用Post请求表单类型，传送的值可空
     * */
    @PostMapping(value = "/form")
    @ResponseBody
    public TestEntry form(@Nullable @RequestParam("id") Integer id, @Nullable @RequestParam("name") String name) {
        TestEntry test = new TestEntry();
        test.setId(id);
        test.setName(name);
        return test;
    }

    /*
     * 当使用Post请求form-data表单类型，注意在使用PostMan测试时，不要添加Content-Type头，会影响上传时真正使用的Content-Type的
     * */
    @PostMapping(value = "/file")
    public String form(@RequestParam("filename") MultipartFile file) {
        return "fileName:" + file.getOriginalFilename() + " fileSize:" + file.getSize() + " contentType:" + file.getContentType();
    }
}