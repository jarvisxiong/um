<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<div class="rooms" id="roomsHistoryDirectory">
    <div class="title">
        <a href="javascript://" style="float:right; margin-right: 10px; width: 20px; text-align: center;">
            <img src="${url.ctx}/resources/images/mes/x.gif">
        </a>

        <h2>选择会议</h2>
    </div>
    <div class="category">
        <!--<input class="text"/>-->
        <h2>会议来源</h2>
        <ul>
            <li>预约总裁</li>
            <li>会议室-古北</li>
        </ul>
    </div>
    <div class="results">
        <div class="warp">
            <div class="body" style="width: 556px;"></div>
        </div>
    </div>
    <div class="bottom clearfix">
        <button>确定</button>
        <button>退出</button>
    </div>
</div>
<input type="hidden" id="category" value=""/>
<script type="text/javascript">
    $(function () {
        $.ajaxSetup({cache:false});
        //搜索会议
        window.pickedFunc = window.searchMeetings = function () {
            //会议开始时间
            var date = $(".rooms #date").size() == 0 ? "" : $("#date").val();
            //会议室类型
            var category = $("#category").val();
            //远程搜索
            $(".rooms .results").mask('正在搜索！');
            $(".rooms .body").load("mes-meeting-info!historyDirectoryBody.action", {date:date, category:category}, function () {
                $(".rooms .ti span a").click(function () {
                    $(".rooms #date").val($(this).attr("rel"));
                    window.searchMeetings();
                });
                $(".rooms .results").unmask();
            });
            return;
        };
        window.searchMeetings();
        var lis1 = $(".rooms .category li").click(function () {
            var index = lis1.index(this);
            $("#category").val(index);
            if ($(this).hasClass("selected")) {
                $(".rooms #category").val("");
                lis1.removeClass("selected");
            } else {
                lis1.removeClass("selected");
                $(this).addClass("selected");
            }
            $(".rooms #date").val("${helper.currentDate?string("yyyy-MM-dd")}");
            window.searchMeetings();
        });


        $(".rooms div.title a,.rooms div.bottom button:last").click(function () {
            $("#roomsHistoryDirectory").jqmHide();
        });
        $(".rooms div.bottom button:first").click(function () {
            var li = $(".rooms .trs table ul li.selected");
            if (li.size() == 0) {
                alert("请选择！")
            } else {
                seetingsInfos({
                    room:li.attr("room"),
                    applicant:li.attr("applicant"),
                    applicant_name:li.attr("applicant_name"),
                    compere:li.attr("compere"),
                    compere_name:li.attr("compere_name"),
                    recorder:li.attr("recorder"),
                    recorder_name:li.attr("recorder_name"),
                    subject:li.attr("subject"),
                    participators:li.attr("participators"),
                    participators_name:li.attr("participators_name"),
                    part_num:li.attr("part_num"),
                    date:li.attr("date"),
                    start:li.attr("start"),
                    end:li.attr("end")
                });
                $("#roomsHistoryDirectory").jqmHide();
                //设置
            }
        });
    });
</script>
