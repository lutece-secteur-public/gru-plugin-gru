
truncate sqlpage_fragment;
truncate sqlpage_page;
INSERT INTO `sqlpage_fragment` VALUES (1,1,'<div class=\"row\">\r\n<div class=\"col-xs-12 col-md-12\">\r\n<div>\r\n         <a  class=\"btn btn-primary \" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=delaimoyendetraitementtotal\">\r\n<i class=\"glyphicon glyphicon-stats\" ></i>  Délai moyen de traitement des sollicitations </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombresollicitationsparnature\"> <i class=\"glyphicon glyphicon-stats\" ></i>  Nombre de sollicitation par nature </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationsparstatut\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par statut </a>\r\n \r\n           <li class=\" btn dropdown \">\r\n                        <a data-toggle=\"dropdown\"   class=\"dropdown-toggle  btn btn-primary\" href=\"#\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par groupe d\'assignation </a>\r\n                        <ul class=\"dropdown-menu  \">\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupedassignationentity\">Groupe Assignation Entité</a></li>\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupeassignation\">Groupe Assignation Support Entité</a></li>\r\n                        </ul>\r\n            </li> \r\n\r\n        <a class=\"btn btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=tauxdescalade\"><i class=\"fa fa-bar-chart\" ></i>   Taux d\'escalade</a>\r\n</div>\r\n</div>\r\n</div>\r\n<div>\r\n<#list rows as row>\r\n               <h2>Délais moyen de traitement des sollicitations</h2></br></br>\r\n		<div>\r\n				Délai moyen de traitement total de la sollicitation: <strong>${row.cols[0]!} jours</strong>\r\n				\r\n		</div>\r\n</#list>\r\n</div>\r\n</br>','select avg(DATEDIFF(date_close, date_create)) as \"moyen\" FROM ticketing_ticket;','portal','Délai moyen de traitement total de la sollicitation',0,'none'),(2,1,'<div>\r\n<#list rows as row>\r\n		<div>\r\n				Délai moyen de traitement interne de la sollicitation: <strong>information indisponible dans la base de données (temps passé  en attente de retour usager)</strong>\r\n				\r\n		</div>\r\n</#list>\r\n</div>\r\n</br>','select avg(DATEDIFF(date_close, date_create)) as \"moyen\" FROM ticketing_ticket','portal','Délai moyen de traitement interne de la sollicitation',1,'none'),(3,1,'<div>\r\n<#list rows as row>\r\n		<div>\r\n				Délai moyen d’affectation: <strong>délai séparant la date d’affectation de la sollicitation de sa date de création dépend du workflow</strong>\r\n				\r\n		</div>\r\n</#list>\r\n</div>','select avg(DATEDIFF(date_close, date_create)) as \"moyen\" FROM ticketing_ticket;','portal','Délai moyen d’affectation',2,'none'),(4,4,'<div class=\"row\">\r\n<div class=\"col-xs-12 col-md-12\">\r\n<div>\r\n         <a  class=\"btn btn-primary \" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=delaimoyendetraitementtotal\">\r\n<i class=\"glyphicon glyphicon-stats\" ></i>  Délai moyen de traitement des sollicitations </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombresollicitationsparnature\"> <i class=\"glyphicon glyphicon-stats\" ></i>  Nombre de sollicitation par nature </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationsparstatut\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par statut </a>\r\n\r\n           <li class=\" dropdown btn \">\r\n                        <a data-toggle=\"dropdown\"  class=\"dropdown-toggle btn btn-primary\" href=\"#\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par groupe d\'assignation </a>\r\n                        <ul class=\"dropdown-menu\">\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupedassignationentity\">Groupe Assignation Entité</a></li>\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupeassignation\">Groupe Assignation Support Entité</a></li>\r\n                        </ul>\r\n            </li>    \r\n        <a class=\"btn btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=tauxdescalade\"><i class=\"fa fa-bar-chart\" ></i>   Taux d\'escalade</a>\r\n</div>\r\n\r\n<script src=\"js/Chart.min.js\"></script>\r\n<h2>Nombre de sollicitations par groupe d’assignation (Entité de Support)</h2></br>\r\n<div class=\"row\">\r\n<div class=\"col-xs-9 col-sm-9 col-md-9 col-lg-9\">\r\n<table class=\"table table-condensed table-striped\">\r\n    <thead>\r\n    <tr>\r\n        <th>Entité Support</th>\r\n        <th>Nombre de sollicitations</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n		<#list rows as row>\r\n		<tr>\r\n			<td>${row.cols[0]}</td>\r\n			<td>${row.cols[1]}</td>\r\n		</tr>\r\n		\r\n		</#list>\r\n	</tbody>\r\n	</table>\r\n</div>\r\n</div>\r\n</div>\r\n<div class=\"row\">\r\n\r\n<div class=col-xs-3 col-sm-3 col-md-3 col-lg-3\">\r\n\r\n	<div id=\"canvas-holder\">\r\n                    <div id=\"canvas\" ></div>\r\n       </div>\r\n  \r\n\r\n</div>\r\n\r\n<div class=\"btn-group-vertical\">\r\n<a href=\'javascript:doughnut( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Doughnut</a>\r\n<a href=\'javascript:pie( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Pie </a>\r\n<a href=\'javascript:bar( )\' class=\"btn btn-default\"> <i class=\"fa fa-bar-chart\" ></i> Bar</a>\r\n</div>\r\n       			\r\n<#assign colors = [\"#F7464A\",\"#46BFBD\",\"#FDB45C\", \"#949FB1\",\"#4D5360\" ] />\r\n<#assign highlights = [\"#FF5A5E\",\"#5AD3D1\",\"#FFC870\", \"#A8B3C5\",\"#616774\" ] />\r\n<script>\r\nvar doughnutData = [\r\n<#list rows as row>\r\n    <#if row_index != 0> , </#if>				\r\n    {\r\n	value: ${row.cols[1]},\r\n	color:\"${colors[row_index % 5]}\",\r\n	highlight: \"${highlights[row_index % 5]}\",\r\n	label: \"${row.cols[0]}\"\r\n    }\r\n</#list>\r\n];\r\nvar barChartData = {\r\n		labels : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[0]}\"</#list> ],\r\n		datasets : [\r\n			{\r\n				fillColor : \"rgba(100,100,220,0.8)\",\r\n				strokeColor : \"rgba(100,100,220,0.9)\",\r\n				highlightFill: \"rgba(100,100,220,0.9)\",\r\n				highlightStroke: \"rgba(100,100,220,1)\",\r\n				data : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[1]}\"</#list>]\r\n			}		]\r\n\r\n};\r\n\r\nwindow.onload = function(){\r\n    doughnut();\r\n};\r\n\r\nfunction doughnut( ) {\r\n        $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n	var ctx2 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.myDoughnut = new Chart(ctx2).Doughnut(doughnutData, {responsive : true});\r\n         \r\n};\r\n\r\nfunction pie( ) {\r\n         $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n        document.getElementById(\"chart-area\").getContext(\"2d\").clearRect(0, 0, 400, 400);\r\n	var ctx1 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.Pie = new Chart(ctx1).Pie(doughnutData, {responsive : true});\r\n        \r\n};\r\nfunction bar( ) {\r\n                $( \"#chart-area\" ).remove(  );\r\n                $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n		var ctx3 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n		window.myBar = new Chart(ctx3).Bar(barChartData, {\r\n			responsive : true, animationSteps: 50\r\n	         });\r\n};\r\n</script>\r\n</div> \r\n</div>\r\n</div> <!-- row end -->','select us.name, count(distinct t.id_ticket) from unittree_unit u JOIN ticketing_ticket t ON (u.id_unit = t.id_unit) JOIN ticketing_support_entity us ON us.id_unit = u.id_unit  group by (us.name) ;','portal','Nombre de sollicitations par groupe d’assignation (Entité de Support)',0,'none'),(5,3,'<div class=\"row\">\r\n<div class=\"col-xs-12 col-md-12\">\r\n<div>\r\n         <a  class=\"btn btn-primary \" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=delaimoyendetraitementtotal\">\r\n<i class=\"glyphicon glyphicon-stats\" ></i>  Délai moyen de traitement des sollicitations </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombresollicitationsparnature\"> <i class=\"glyphicon glyphicon-stats\" ></i>  Nombre de sollicitation par nature </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationsparstatut\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par statut </a>\r\n\r\n           <li class=\" dropdown btn \">\r\n                        <a data-toggle=\"dropdown\"  class=\"dropdown-toggle btn btn-primary\" href=\"#\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par groupe d\'assignation </a>\r\n                        <ul class=\"dropdown-menu\">\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupedassignationentity\">Groupe Assignation Entité</a></li>\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupeassignation\">Groupe Assignation Support Entité</a></li>\r\n                        </ul>\r\n            </li>    \r\n        <a class=\"btn btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=tauxdescalade\"><i class=\"fa fa-bar-chart\" ></i>   Taux d\'escalade</a>\r\n</div>\r\n\r\n<script src=\"js/Chart.min.js\"></script>\r\n<h2>Nombre de sollicitations par statut</h2></br>\r\n<div class=\"row\">\r\n<div class=\"col-xs-9 col-sm-9 col-md-9 col-lg-9\">\r\n<table class=\"table table-condensed table-striped\">\r\n    <thead>\r\n    <tr>\r\n        <th>Statut</th>\r\n        <th>Nombre de sollicitations</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n		<#list rows as row>\r\n		<tr>\r\n			<td>${row.cols[0]}</td>\r\n			<td>${row.cols[1]}</td>\r\n		</tr>\r\n		\r\n		</#list>\r\n	</tbody>\r\n	</table>\r\n</div>\r\n</div>\r\n</div>\r\n<div class=\"row\">\r\n\r\n<div class=col-xs-3 col-sm-3 col-md-3 col-lg-3\">\r\n\r\n	<div id=\"canvas-holder\">\r\n                    <div id=\"canvas\" ></div>\r\n       </div>\r\n  \r\n\r\n</div>\r\n\r\n<div class=\"btn-group-vertical\">\r\n<a href=\'javascript:doughnut( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Doughnut</a>\r\n<a href=\'javascript:pie( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Pie </a>\r\n<a href=\'javascript:bar( )\' class=\"btn btn-default\"> <i class=\"fa fa-bar-chart\" ></i> Bar</a>\r\n</div>\r\n       			\r\n<#assign colors = [\"#F7464A\",\"#46BFBD\",\"#FDB45C\", \"#949FB1\",\"#4D5360\" ] />\r\n<#assign highlights = [\"#FF5A5E\",\"#5AD3D1\",\"#FFC870\", \"#A8B3C5\",\"#616774\" ] />\r\n<script>\r\nvar doughnutData = [\r\n<#list rows as row>\r\n    <#if row_index != 0> , </#if>				\r\n    {\r\n	value: ${row.cols[1]},\r\n	color:\"${colors[row_index % 5]}\",\r\n	highlight: \"${highlights[row_index % 5]}\",\r\n	label: \"${row.cols[0]}\"\r\n    }\r\n</#list>\r\n];\r\nvar barChartData = {\r\n		labels : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[0]}\"</#list> ],\r\n		datasets : [\r\n			{\r\n				fillColor : \"rgba(100,100,220,0.8)\",\r\n				strokeColor : \"rgba(100,100,220,0.9)\",\r\n				highlightFill: \"rgba(100,100,220,0.9)\",\r\n				highlightStroke: \"rgba(100,100,220,1)\",\r\n				data : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[1]}\"</#list>]\r\n			}		]\r\n\r\n};\r\n\r\nwindow.onload = function(){\r\n    doughnut();\r\n};\r\n\r\nfunction doughnut( ) {\r\n        $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n	var ctx2 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.myDoughnut = new Chart(ctx2).Doughnut(doughnutData, {responsive : true});\r\n         \r\n};\r\n\r\nfunction pie( ) {\r\n         $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n        document.getElementById(\"chart-area\").getContext(\"2d\").clearRect(0, 0, 400, 400);\r\n	var ctx1 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.Pie = new Chart(ctx1).Pie(doughnutData, {responsive : true});\r\n        \r\n};\r\nfunction bar( ) {\r\n                $( \"#chart-area\" ).remove(  );\r\n                $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n		var ctx3 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n		window.myBar = new Chart(ctx3).Bar(barChartData, {\r\n			responsive : true, animationSteps: 50\r\n	         });\r\n};\r\n</script>\r\n</div> \r\n</div>\r\n</div> <!-- row end -->','select ticket_status_text, count(distinct id_ticket) from ticketing_ticket group by (ticket_status_text);','portal','Nombre de sollicitations par statut',0,'none'),(8,5,'<div class=\"row\">\r\n<div class=\"col-xs-12 col-md-6\">\r\n<script src=\"js/Chart.min.js\"></script>\r\n<h2>Nombre de sollicitations par groupe d’assignation (Entity de Support)</h2>\r\n<div class=\"row\">\r\n<div class=\"col-xs-12 col-sm-12 col-md-12 col-lg-12\">\r\n<table class=\"table table-condensed table-striped\">\r\n    <thead>\r\n    <tr>\r\n        <th>Entity Support</th>\r\n         <th>Date de création</th>\r\n        <th>Nombre de sollicitations</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n		<#list rows as row>\r\n		<tr>\r\n			<td>${row.cols[0]}</td>\r\n			<td>${row.cols[1]}</td>\r\n                        <td>${row.cols[2]}</td>\r\n		</tr>\r\n		\r\n		</#list>\r\n	</tbody>\r\n	</table>\r\n</div>\r\n<div class=\"row\">\r\n<div class=col-xs-9 col-sm-9 col-md-9 col-lg-9\">\r\n<h2> Graphique</h2>\r\n		<div >\r\n			<canvas id=\"canvasEntity\" height=\"400\" width=\"600\"></canvas>\r\n		</div>\r\n        </div>\r\n</div>\r\n\r\n	<script>\r\n	var barChartData2 = {\r\n		labels : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[0]}\"</#list> ],\r\n		datasets : [\r\n			{\r\n				fillColor : \"rgba(100,100,220,0.8)\",\r\n				strokeColor : \"rgba(100,100,220,0.9)\",\r\n				highlightFill: \"rgba(100,100,220,0.9)\",\r\n				highlightStroke: \"rgba(100,100,220,1)\",\r\n				data : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[2]}\"</#list>]\r\n			}		]\r\n\r\n	}\r\n	window.onload = function(){\r\n		var context = document.getElementById(\"canvasEntity\").getContext(\"2d\");\r\n		window.myBa = new Chart(context).Bar(barChartData2, {\r\n			responsive : true, animationSteps: 50\r\n		});\r\n	}\r\n\r\n	</script>\r\n</row>\r\n</div>\r\n</div>','select us.name, date_create ,count(distinct t.id_ticket) from unittree_unit u JOIN ticketing_ticket t ON (u.id_unit = t.id_unit) JOIN ticketing_support_entity us ON us.id_unit = u.id_unit  where (t.date_create >  \'@param1@\' ) group by (us.name) ;','portal','Test Demo',2,'none'),(10,6,'<div class=\"row\">\r\n<div class=\"col-xs-9 col-md-4\">\r\n<#list rows as row>\r\n                <#if !nbSollicitationEscl??>\r\n		    <#assign nbSollicitationEscl = row.cols[0]?number >\r\n                <#else>\r\n                    <#assign nbSollicitationTotal= row.cols[0]?number >\r\n                </#if>\r\n</#list>\r\n\r\n<#if nbSollicitationTotal == 0>\r\n  <#assign nbSollicitationTotal= 1 >\r\n  <#assign nbSollicitationEscl = 1>\r\n</#if>\r\n<#assign taux= (nbSollicitationEscl / nbSollicitationTotal) >\r\n<div>\r\n          Taux d’escalade: <strong>${nbSollicitationEscl!} / ${nbSollicitationTotal!} =${taux}  sollicitations</strong>\r\n</div>\r\n</br>\r\n</div>\r\n</div>\r\n<script src=\"js/Chart.min.js\"></script>\r\n<div class=\"row\">\r\n<div class=col-xs-3 col-sm-3 col-md-3 col-lg-3\">\r\n	<div id=\"canvas-holder\">\r\n			<div id=\"canvas\" ></div>\r\n		</div>\r\n</div>\r\n\r\n<div class=\"btn-group-vertical\">\r\n<a href=\'javascript:doughnut( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Doughnut</a>\r\n<a href=\'javascript:pie( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Pie </a>\r\n<a href=\'javascript:bar( )\' class=\"btn btn-default\"> <i class=\"fa fa-bar-chart\" ></i> Bar</a>\r\n</div>\r\n       			\r\n\r\n<#assign colors = [\"#F7464A\",\"#46BFBD\",\"#FDB45C\", \"#949FB1\",\"#4D5360\" ] />\r\n<#assign highlights = [\"#FF5A5E\",\"#5AD3D1\",\"#FFC870\", \"#A8B3C5\",\"#616774\" ] />\r\n<script>\r\nvar doughnutData = [		\r\n    {\r\n	value:${nbSollicitationEscl},\r\n	color:\"${colors[0 % 5]}\",\r\n	highlight: \"${highlights[0 % 5]}\",\r\n	label: \"Sollicitations Escaladées\"\r\n    },\r\n   {\r\n	value: ${nbSollicitationTotal - nbSollicitationEscl},\r\n	color:\"${colors[1 % 5]}\",\r\n	highlight: \"${highlights[1 % 5]}\",\r\n	label: \"Sollicitation non Escaladées\"\r\n    }\r\n];\r\n\r\n\r\nvar barChartData = {\r\n		labels : [\"Sollicitation Escaladées\", \"Sollicitation non Escaladées\"  ],\r\n		datasets : [\r\n			{\r\n				fillColor : \"rgba(100,100,220,0.8)\",\r\n				strokeColor : \"rgba(100,100,220,0.9)\",\r\n				highlightFill: \"rgba(100,100,220,0.9)\",\r\n				highlightStroke: \"rgba(100,100,220,1)\",\r\n				data : [${nbSollicitationEscl}, ${nbSollicitationTotal - nbSollicitationEscl} ]\r\n			}		]\r\n\r\n};\r\n\r\nwindow.onload = function(){\r\n    doughnut();\r\n};\r\n\r\nfunction doughnut( ) {\r\n        $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n	var ctx2 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.myDoughnut = new Chart(ctx2).Doughnut(doughnutData, {responsive : true});\r\n         \r\n};\r\n\r\nfunction pie( ) {\r\n         $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n        document.getElementById(\"chart-area\").getContext(\"2d\").clearRect(0, 0, 400, 400);\r\n	var ctx1 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.Pie = new Chart(ctx1).Pie(doughnutData, {responsive : true});\r\n        \r\n};\r\nfunction bar( ) {\r\n                $( \"#chart-area\" ).remove(  );\r\n                $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n		var ctx3 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n		window.myBar = new Chart(ctx3).Bar(barChartData, {\r\n			responsive : true, animationSteps: 50\r\n	         });\r\n};\r\n\r\n</script>\r\n</div> \r\n</div> <!-- row end -->','select count(distinct wh.id_resource) from workflow_resource_history wh JOIN workflow_task wt ON (wt.id_action = wh.id_action AND wt.id_task = 381)  UNION ALL select count(distinct id_ticket ) from ticketing_ticket ;','portal','Taux d’escalade',3,'none'),(11,6,'<div class=\"row\">\r\n<div class=\"col-xs-12 col-md-12\">\r\n<div>\r\n         <a  class=\"btn btn-primary \" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=delaimoyendetraitementtotal\">\r\n<i class=\"glyphicon glyphicon-stats\" ></i>  Délai moyen de traitement des sollicitations </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombresollicitationsparnature\"> <i class=\"glyphicon glyphicon-stats\" ></i>  Nombre de sollicitation par nature </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationsparstatut\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par statut </a>\r\n\r\n           <li class=\" dropdown btn \">\r\n                        <a data-toggle=\"dropdown\"  class=\"dropdown-toggle btn btn-primary\" href=\"#\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par groupe d\'assignation </a>\r\n                        <ul class=\"dropdown-menu\">\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupedassignationentity\">Groupe Assignation Entité</a></li>\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupeassignation\">Groupe Assignation Support Entité</a></li>\r\n                        </ul>\r\n            </li>    \r\n        <a class=\"btn btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=tauxdescalade\"><i class=\"fa fa-bar-chart\" ></i>   Taux d\'escalade</a>\r\n</div>\r\n</div>\r\n</div>\r\n<div class=\"row \">\r\n\r\n<div class=\"col-xs-12 col-md-12\">\r\n<h2>Taux D\'escalade</h2>\r\n</br></br>\r\n<#list rows as row>\r\n		<div>\r\n                          Nombre de sollicitations escaladées:    <strong> ${row.cols[0]!} sollicitations</strong>\r\n				\r\n		</div>\r\n</#list>\r\n\r\n</div>\r\n</div>\r\n</br>','select count(distinct wh.id_resource) from workflow_resource_history wh JOIN workflow_task wt ON (wt.id_action = wh.id_action AND wt.id_task = 381);','portal','Nombre de sollicitations escaladées',1,'none'),(12,6,'<div class=\"row\">\r\n<div class=\"col-xs-9 col-md-4\">\r\n<#list rows as row>\r\n		<div>\r\n                         Nombre total de sollicitations :  <strong> ${row.cols[0]!} sollicitations</strong>\r\n				\r\n		</div>\r\n</#list>\r\n</div>\r\n</div>\r\n</br>','select count(distinct t.id_ticket) from ticketing_ticket t;','portal','Nombre total de sollicitations',2,'none'),(14,8,'<div class=\"row\">\r\n<div class=\"col-xs-12 col-md-12\">\r\n<div>\r\n         <a  class=\"btn btn-primary \" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=delaimoyendetraitementtotal\">\r\n<i class=\"glyphicon glyphicon-stats\" ></i>  Délai moyen de traitement des sollicitations </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombresollicitationsparnature\"> <i class=\"glyphicon glyphicon-stats\" ></i>  Nombre de sollicitation par nature </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationsparstatut\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par statut </a>\r\n\r\n           <li class=\" dropdown btn\">\r\n                        <a data-toggle=\"dropdown\"  class=\"dropdown-toggle btn btn-primary\" href=\"#\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par groupe d\'assignation </a>\r\n                        <ul class=\"dropdown-menu\">\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupedassignationentity\">Groupe Assignation Entité</a></li>\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupeassignation\">Groupe Assignation Support Entité</a></li>\r\n                        </ul>\r\n            </li>    \r\n        <a class=\"btn btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=tauxdescalade\"><i class=\"fa fa-bar-chart\" ></i>   Taux d\'escalade</a>\r\n</div>\r\n\r\n<script src=\"js/Chart.min.js\"></script>\r\n<h2>Nombre de sollicitations par groupe d’assignation (Entité)</h2></br>\r\n<div class=\"row\">\r\n<div class=\"col-xs-9 col-sm-9 col-md-9 col-lg-9\">\r\n<table class=\"table table-condensed table-striped\">\r\n    <thead>\r\n    <tr>\r\n        <th>Entité </th>\r\n        <th>Nombre de sollicitations</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n		<#list rows as row>\r\n		<tr>\r\n			<td>${row.cols[0]}</td>\r\n			<td>${row.cols[1]}</td>\r\n		</tr>\r\n		\r\n		</#list>\r\n	</tbody>\r\n	</table>\r\n</div>\r\n</div>\r\n</div>\r\n<div class=\"row\">\r\n<div class=col-xs-3 col-sm-3 col-md-3 col-lg-3\">\r\n	<div id=\"canvas-holder\">\r\n			<div id=\"canvas\" ></div>\r\n		</div>\r\n</div>\r\n\r\n<div class=\"btn-group-vertical\">\r\n<a href=\'javascript:doughnut( );\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Doughnut</a>\r\n<a href=\'javascript:pie( );\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Pie </a>\r\n<a href=\'javascript:bar( );\' class=\"btn btn-default\"> <i class=\"fa fa-bar-chart\" ></i> Bar</a>\r\n</div>\r\n\r\n<#assign colors = [\"#F7464A\",\"#46BFBD\",\"#FDB45C\", \"#949FB1\",\"#4D5360\" ] />\r\n<#assign highlights = [\"#FF5A5E\",\"#5AD3D1\",\"#FFC870\", \"#A8B3C5\",\"#616774\" ] />\r\n<script>\r\nvar doughnutData = [\r\n<#list rows as row>\r\n    <#if row_index != 0> , </#if>				\r\n    {\r\n	value: ${row.cols[1]},\r\n	color:\"${colors[row_index % 5]}\",\r\n	highlight: \"${highlights[row_index % 5]}\",\r\n	label: \"${row.cols[0]}\"\r\n    }\r\n</#list>\r\n];\r\nvar barChartData = {\r\n		labels : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[0]}\"</#list> ],\r\n		datasets : [\r\n			{\r\n				fillColor : \"rgba(100,100,220,0.8)\",\r\n				strokeColor : \"rgba(100,100,220,0.9)\",\r\n				highlightFill: \"rgba(100,100,220,0.9)\",\r\n				highlightStroke: \"rgba(100,100,220,1)\",\r\n				data : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[1]}\"</#list>]\r\n			}		]\r\n\r\n};\r\n\r\nwindow.onload = function(){\r\n    doughnut();\r\n};\r\n\r\nfunction doughnut( ) {\r\n        $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n	var ctx2 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.myDoughnut = new Chart(ctx2).Doughnut(doughnutData, {responsive : true});\r\n         \r\n};\r\n\r\nfunction pie( ) {\r\n         $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n        document.getElementById(\"chart-area\").getContext(\"2d\").clearRect(0, 0, 400, 400);\r\n	var ctx1 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.Pie = new Chart(ctx1).Pie(doughnutData, {responsive : true});\r\n        \r\n};\r\nfunction bar( ) {\r\n                $( \"#chart-area\" ).remove(  );\r\n                $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n		var ctx3 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n		window.myBar = new Chart(ctx3).Bar(barChartData, {\r\n			responsive : true, animationSteps: 50\r\n	         });\r\n};\r\n</script>\r\n</div>\r\n</div> \r\n</div> <!-- row end -->','select u.label, count(t.id_unit) from unittree_unit u JOIN ticketing_ticket t ON u.id_unit = t.id_unit group by (u.label);','portal','Nombre de sollicitations par groupe d’assignation (Entité)',1,'none'),(15,2,'<div class=\"row\">\r\n<div class=\"col-xs-12 col-md-12\">\r\n<div>\r\n         <a  class=\"btn btn-primary \" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=delaimoyendetraitementtotal\">\r\n<i class=\"glyphicon glyphicon-stats\" ></i>  Délai moyen de traitement des sollicitations </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombresollicitationsparnature\"> <i class=\"glyphicon glyphicon-stats\" ></i>  Nombre de sollicitation par nature </a>\r\n         <a class=\"btn  btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationsparstatut\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par statut </a>\r\n\r\n           <li class=\" dropdown btn  \">\r\n                        <a data-toggle=\"dropdown\"  class=\"dropdown-toggle btn btn-primary\" href=\"#\"> <i class=\"fa fa-bar-chart\" ></i>  Nombre de sollicitations par groupe d\'assignation </a>\r\n                        <ul class=\"dropdown-menu\">\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupedassignationentity\">Groupe Assignation Entité</a></li>\r\n                            <li><a href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=nombredesollicitationspargroupeassignation\">Groupe Assignation Support Entité</a></li>\r\n                        </ul>\r\n            </li>    \r\n        <a class=\"btn btn-primary\" href=\"jsp/admin/plugins/sqlpage/ManageSQLPages.jsp?view=showSQLPage&sqlpage=tauxdescalade\"><i class=\"fa fa-bar-chart\" ></i>   Taux d\'escalade</a>\r\n</div>\r\n\r\n<script src=\"js/Chart.min.js\"></script>\r\n<h2>Nombre de sollicitations par nature</h2></br>\r\n<div class=\"row\">\r\n<div class=\"col-xs-9 col-sm-9 col-md-9 col-lg-9\">\r\n<table class=\"table table-condensed table-striped\">\r\n    <thead>\r\n    <tr>\r\n        <th>Nature</th>\r\n        <th>Nombre de sollicitations</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n		<#list rows as row>\r\n		<tr>\r\n			<td>${row.cols[0]}</td>\r\n			<td>${row.cols[1]}</td>\r\n		</tr>\r\n		\r\n		</#list>\r\n	</tbody>\r\n	</table>\r\n</div>\r\n</div>\r\n</div>\r\n\r\n<div class=\"row\">\r\n\r\n<div class=col-xs-3 col-sm-3 col-md-3 col-lg-3\">\r\n\r\n	<div id=\"canvas-holder\">\r\n                    <div id=\"canvas\" ></div>\r\n       </div>\r\n  \r\n\r\n</div>\r\n\r\n<div class=\"btn-group-vertical\">\r\n<a href=\'javascript:doughnut( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Doughnut</a>\r\n<a href=\'javascript:pie( )\' class=\"btn btn-default\"> <i class=\"icon-adjust\" ></i> Pie </a>\r\n<a href=\'javascript:bar( )\' class=\"btn btn-default\"> <i class=\"fa fa-bar-chart\" ></i> Bar</a>\r\n</div>\r\n       			\r\n<#assign colors = [\"#F7464A\",\"#46BFBD\",\"#FDB45C\", \"#949FB1\",\"#4D5360\" ] />\r\n<#assign highlights = [\"#FF5A5E\",\"#5AD3D1\",\"#FFC870\", \"#A8B3C5\",\"#616774\" ] />\r\n<script>\r\nvar doughnutData = [\r\n<#list rows as row>\r\n    <#if row_index != 0> , </#if>				\r\n    {\r\n	value: ${row.cols[1]},\r\n	color:\"${colors[row_index % 5]}\",\r\n	highlight: \"${highlights[row_index % 5]}\",\r\n	label: \"${row.cols[0]}\"\r\n    }\r\n</#list>\r\n];\r\nvar barChartData = {\r\n		labels : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[0]}\"</#list> ],\r\n		datasets : [\r\n			{\r\n				fillColor : \"rgba(100,100,220,0.8)\",\r\n				strokeColor : \"rgba(100,100,220,0.9)\",\r\n				highlightFill: \"rgba(100,100,220,0.9)\",\r\n				highlightStroke: \"rgba(100,100,220,1)\",\r\n				data : [<#list rows as row><#if row_index !=0>,</#if> \"${row.cols[1]}\"</#list>]\r\n			}		]\r\n\r\n};\r\n\r\nwindow.onload = function(){\r\n    doughnut();\r\n};\r\n\r\nfunction doughnut( ) {\r\n        $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n	var ctx2 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.myDoughnut = new Chart(ctx2).Doughnut(doughnutData, {responsive : true});\r\n         \r\n};\r\n\r\nfunction pie( ) {\r\n         $( \"#chart-area\" ).remove(  );\r\n         $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n        document.getElementById(\"chart-area\").getContext(\"2d\").clearRect(0, 0, 400, 400);\r\n	var ctx1 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n	window.Pie = new Chart(ctx1).Pie(doughnutData, {responsive : true});\r\n        \r\n};\r\nfunction bar( ) {\r\n                $( \"#chart-area\" ).remove(  );\r\n                $( \"#canvas\" ).append(\" <canvas id=\'chart-area\' height=\'400\' width=\'400\'></canvas>\" );\r\n		var ctx3 = document.getElementById(\"chart-area\").getContext(\"2d\");\r\n		window.myBar = new Chart(ctx3).Bar(barChartData, {\r\n			responsive : true, animationSteps: 50\r\n	         });\r\n};\r\n</script>\r\n</div> \r\n</div>\r\n</div> <!-- row end -->','select tp.label , count(distinct t.id_ticket) as nombre\r\nfrom ticketing_ticket t\r\nJOIN ticketing_ticket_category c ON c.id_ticket_category= t.id_ticket_category \r\nJOIN ticketing_ticket_domain d ON d.id_ticket_domain= c.id_ticket_domain \r\nJOIN ticketing_ticket_type tp ON tp.id_ticket_type= d.id_ticket_type group by tp.label;','portal','Nombre de sollicitations par nature',0,'none');
INSERT INTO `sqlpage_page` VALUES (1,'delaimoyendetraitementtotal','Délai moyen de traitement des sollicitations','Délai moyen de traitement des sollicitaions','all'),(2,'nombresollicitationsparnature','Nombre de sollicitations par nature','Nombre de sollicitations par nature','all'),(3,'nombredesollicitationsparstatut','Nombre de sollicitations par statut','Nombre de sollicitations par statut','all'),(4,'nombredesollicitationspargroupeassignation','Nombre de sollicitations par groupe d’assignation (Entité Support)','Nombre de sollicitations par groupe d’assignation','all'),(5,'testdemo','test demo','test demo','all'),(6,'tauxdescalade','Taux d’escalade','Taux d’escalade = Nombre de sollicitations escaladées / nombre total de sollicitations','all'),(8,'nombredesollicitationspargroupedassignationentity',' Nombre de sollicitations par groupe d’assignation (Entité)',' Nombre de sollicitations par groupe d’assignation (Entité)','all');


