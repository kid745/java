<div class="modal modal-login fade" id="ere_signin_modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
							<ul class="nav nav-tabs">
					<li class="active">
						<a id="ere_login_modal_tab" href="#login" data-toggle="tab">Log in</a>
					</li>
					<li><a id="ere_register_modal_tab" href="#register" data-toggle="tab">Register</a>
					</li>
				</ul>
				<div class="tab-content ">
					<div class="tab-pane active" id="login">
						<div class="ere-login-wrap">
	<div class="ere_messages message"></div>
	<form class="ere-login" method="post" enctype="multipart/form-data" novalidate="novalidate">
		<div class="form-group control-username">
			<input name="user_login" class="form-control control-icon login_user_login" placeholder="Username or email address" type="text">
		</div>
		<div class="form-group control-password">
			<input name="user_password" class="form-control control-icon" placeholder="Password" type="password">
		</div>
		<div class="checkbox">
			<label>
				<input name="remember" type="checkbox">
				Remember me			</label>
		</div>
		<input type="hidden" name="ere_security_login" value="8d710ad475">
		<input type="hidden" name="action" value="ere_login_ajax">
		<a href="javascript:void(0)" class="ere-reset-password">Lost password</a>
				<button type="submit" data-redirect-url="" class="ere-login-button btn btn-primary btn-block">Login</button>
	</form>
	<hr>
	</div>
<div class="ere-reset-password-wrap" style="display: none">
	<div class="ere-resset-password-wrap">
	<div class="ere_messages message ere_messages_reset_password"></div>
	<form method="post" enctype="multipart/form-data">
		<div class="form-group control-username">
			<input name="user_login" class="form-control control-icon reset_password_user_login" placeholder="Enter your username or email">
			<input type="hidden" name="ere_security_reset_password" value="8e73375480">
			<input type="hidden" name="action" value="ere_reset_password_ajax">
						<button type="submit" class="btn btn-primary btn-block ere_forgetpass">Get new password</button>
		</div>
	</form>
</div>
	<a href="javascript:void(0)" class="ere-back-to-login">Back to Login</a>
</div>					</div>
					<div class="tab-pane" id="register">
						<div class="ere-register-wrap">
	<div class="ere_messages message"></div>
	<form class="ere-register" method="post" enctype="multipart/form-data" novalidate="novalidate">
		<div class="form-group control-username">
			<input name="user_login" class="form-control control-icon" type="text" placeholder="Username">
		</div>
		<div class="form-group control-email">
			<input name="user_email" type="email" class="form-control control-icon" placeholder="Email">
		</div>

				<div class="form-group control-term-condition">
			<div class="checkbox">
				<label>
					<input name="term_condition" type="checkbox">
					I agree with your <a target="_blank" href="https://www.ammuthemes.com/real-estate-salient-pro/">Terms &amp; Conditions</a>				</label>
			</div>
		</div>
				<input type="hidden" name="ere_register_security" value="f2dd0694eb">
		<input type="hidden" name="action" value="ere_register_ajax">
		<button type="submit" data-redirect-url="" class="ere-register-button btn btn-primary btn-block">Register</button>
	</form>
</div>
					</div>
				</div>
					</div>
	</div>
</div>