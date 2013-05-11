//闭包防止变量定义冲突
/**
 * 主窗体 自适应函数定义
 * 默认情况下 主窗口最小高度为 604px, 最大高度以当前标签页内iframe的高度+124px(头部Tabs的高度)。
 *
 */
(function (window) {

    window.autoAdapter = autoAdapter = {
        html:{
            left:$(".div_left,.div_center"),
            tabs:{
                currentTab:function () {
                    return $('#' + top.nowMenuId + '_frame');
                }, currentTabHeight:function () {
                    try {
                        return $(this.currentTab().get(0).contentWindow.document.body).height();
                    } catch (ex) {
                        return 0;
                    }
                }
            },
            body:$("#bodyLoad")
        }, resize:function () {
            try {
                //当前主窗口Iframe高度
                var currentIframeHeight=t1 = autoAdapter.html.tabs.currentTabHeight();
                var windowHeight = $(window).height();
                var bodyHeihgt = $("body").height();
                var tempHeight = windowHeight > bodyHeihgt ? windowHeight : bodyHeihgt;
                if (currentIframeHeight == 0) {
                    currentIframeHeight = tempHeight;
                } else if (windowHeight > currentIframeHeight) {
                    currentIframeHeight = windowHeight;
                }
                autoAdapter.html.left.height(currentIframeHeight +(t1==0?0: 50));
                autoAdapter.html.tabs.currentTab().height(currentIframeHeight);
                //autoAdapter.html.body.height("auto");
            } catch (ex) {
                alert(ex.message);
            }
        }
    }

    setInterval(autoAdapter.resize, 400);
    autoAdapter.resize();
})(window);