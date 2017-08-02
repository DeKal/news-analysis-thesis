		<div class="pager fr">
			<%
				final int CPAGE = (int) request.getAttribute("page");
				if (CPAGE > 0)
					pageContext.setAttribute("prevPage", CPAGE -1 );
				else {
					pageContext.setAttribute("prevPage", CPAGE);
					pageContext.setAttribute("display", "hide");
				}
					
			%>
				<a href="/web/page/${prevPage}" class = "${display}" rel="prev" title="go to page ${prevPage}"> 
					<span class="page-numbers prev">prev </span>
				</a>
				
			<% 	
				int count = 0;
				int pI = CPAGE-2;
				if (pI<0) {
					pI = 0;
				}
				while (count++ < 5){
					
					if (pI == CPAGE){
						pageContext.setAttribute("current", pI);
						
			%>
						<span class="page-numbers current"> ${current}</span>
				
			<% 
					}
					else{
						pageContext.setAttribute("pI", pI);
			%>
						<a href="/web/page/${pI}" title="go to page ${pI}">
							<span class="page-numbers">${pI}</span>
						</a> 
			<% 
					}
					pI++;
				}
				pageContext.setAttribute("nextPage", CPAGE + 1);
			%>

				<span class="page-numbers dots">...</span> 
				
				<a href="/web/page/${nextPage}" rel="next"> <span class="page-numbers next">
						next</span>
				</a>

			</div>
