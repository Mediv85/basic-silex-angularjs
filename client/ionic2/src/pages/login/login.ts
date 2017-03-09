export class LoginAuth{
  constructor(
    public username: String="",
    public password: String="",
  ){}
}

export class LoginResp{
  constructor(
    public success: Boolean,
    public token: String,
    public username: String,
  ){}

}
