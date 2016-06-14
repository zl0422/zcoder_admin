<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--自定义validator-->
<script>
    $('form').submit(function(e) {
        e.preventDefault();
        var submit = true;
        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }
        if (submit)
            this.submit();
        return false;
    });
</script>