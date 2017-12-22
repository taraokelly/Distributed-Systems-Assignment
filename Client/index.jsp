<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:32pt; font-family:arial; color:#990000; font-weight:bold">Dictionary Lookup Service</div>

</p>&nbsp;</p>&nbsp;</p>

<table width="600" cellspacing="0" cellpadding="7" border="0">
	<tr>
		<td valign="top">

			<form bgcolor="white" method="GET" enctype="multipart/form-data" action="doProcess">
				<fieldset>
					<legend><h3>Search</h3></legend>
					<b>Word:</b><br>
					<center><input name="searchStr" type="text" size="90"/></center>
					<p/>
					<center><input type="submit" value="Submit"></center>
					<br>
				</fieldset>							
			</form>	

		</td>
	</tr>
	<tr>
		<td valign="top">

			<form bgcolor="white" method="POST" enctype="multipart/form-data">
				<fieldset>
					<legend><h3>Add</h3></legend>
					<b>Word:</b><br>
					<center><input name="addStr" type="text" size="90"/></center>
					<br>
					<b>Description:</b><br>
					<center><input name="addDesc" type="text" size="90"/></center>
					<p/>
					<center><input type="submit" value="Submit"></center>
					<br>
				</fieldset>							
			</form>	

		</td>
	</tr>
	<tr>
			<td valign="top">
				
				<form bgcolor="white" method="POST" enctype="multipart/form-data">
					<fieldset>
						<legend><h3>Delete</h3></legend>
						<b>Word:</b><br>
						<center><input name="deleteStr" type="text" size="90"/></center>
						<p/>
						<center><input type="submit" value="Submit"></center>
						<br>
					</fieldset>							
				</form>	
	
			</td>
		</tr>
	<tr>
			<td valign="top">
				
				<form bgcolor="white" method="POST" enctype="multipart/form-data">
					<fieldset>
						<legend><h3>Modify</h3></legend>
						<b>String:</b><br>
						<center><input name="modifyStr" type="text" size="90"/></center>
						<br>
						<b>Description:</b><br>
						<center><input name="modifyDesc" type="text" size="90"/></center>
						<p/>
						<center><input type="submit" value="Submit"></center>
						<br>
					</fieldset>							
				</form>	
	
			</td>
		</tr>
</table>
<%@ include file="includes/footer.jsp" %>

