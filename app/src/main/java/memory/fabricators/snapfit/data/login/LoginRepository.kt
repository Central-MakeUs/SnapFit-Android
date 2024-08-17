package memory.fabricators.snapfit.data.login

abstract class LoginRepository {
    abstract suspend fun login(token: String)
}
