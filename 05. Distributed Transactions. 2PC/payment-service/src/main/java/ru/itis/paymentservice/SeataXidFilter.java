package ru.itis.paymentservice;

import io.seata.core.context.RootContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SeataXidFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String xid = httpRequest.getHeader(RootContext.KEY_XID);

        boolean bound = false;
        if (xid != null) {
            RootContext.bind(xid);
            bound = true;
        }

        try {
            chain.doFilter(request, response);
        } finally {
            if (bound) {
                RootContext.unbind();
            }
        }
    }
}
