/**
 * Application configuration file for WebXml plugin.
 */
webxml {
    filterChainProxyDelegator.add = true

    filterChainProxyDelegator.filterName = "TeeFilter"
    filterChainProxyDelegator.className = "ch.qos.logback.access.servlet.TeeFilter"
    filterChainProxyDelegator.urlPattern = "/*"

//    def contextParam = xml."context-param"
//    contextParam[contextParam.size() - 1] + {
//        'filter' {
//            'filter-name'(config.filterChainProxyDelegator.filterName)
//            'filter-class'(config.filterChainProxyDelegator.className)
//            'init-param' {
//                'param-name'('targetBeanName')
//                'param-value'(config.filterChainProxyDelegator.targetBeanName)
//            }
//        }
//    }


    servlet {
        'servlet-name'('ViewStatusMessages')
        'servlet-class'('ch.qos.logback.classic.ViewStatusMessagesServlet')
        'servlet-mapping' {
            'servlet-name'("ViewStatusMessages")
            'url-pattern'("/logbackStatus")
        }
    }


}
