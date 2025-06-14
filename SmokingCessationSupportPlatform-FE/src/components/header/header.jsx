import { Button, Avatar } from "antd";
import "./header.css";
import { SearchOutlined, UserOutlined } from "@ant-design/icons";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { useSelector } from "react-redux";

function Header() {
  const navigate = useNavigate();
  const location = useLocation();

  const user = useSelector((store) => store.user);

  const isActivePage = (path) => {
    if (path === "/") {
      return location.pathname === "/";
    }
    return location.pathname.includes(path);
  };

  return (
    <>
      <header className="header">
        <div className="header__container">
          <div className="header__inner">
            <a href="/">
              <img
                className="header__inner-logo"
                src="../src/components/images/Quitlt-logo.png"
                alt="Quitlt Logo"
              />
            </a>
            <nav className="header__nav">
              <a href="/" className={isActivePage("/") ? "active" : ""}>
                Home
              </a>

              <a
                href="/user-coach"
                className={isActivePage("/user-coach") ? "active" : ""}
              >
                Coach
              </a>

              <a
                href="/community"
                className={isActivePage("/community") ? "active" : ""}
              >
                Community
              </a>

              <a href="/#" className={isActivePage("/#") ? "active" : ""}>
                Article & Information
              </a>
            </nav>
          </div>
          <div className="header__login-register">
            <SearchOutlined className="search" />
            {user ? (
              // Hiển thị khi user đã đăng nhập
              <div className="user-info">
                {user.avatar ? (
                  <Avatar
                    onClick={() => navigate("/user-profile")}
                    className="user-avatar"
                    size="large"
                    src={user.avatar}
                  />
                ) : (
                  <Avatar
                    onClick={() => navigate("/user-profile")}
                    className="user-avatar"
                    size="large"
                    icon={<UserOutlined />}
                  />
                )}
                <Button
                  type="link"
                  onClick={() => navigate("/user-profile")}
                  className="user-name"
                >
                  {user.profileName}
                </Button>
              </div>
            ) : (
              // Hiển thị khi user chưa đăng nhập
              <>
                <Button
                  type="primary"
                  onClick={() => navigate("/login")}
                  className="login-btn"
                >
                  Login
                </Button>
                <Button
                  color="primary"
                  variant="filled"
                  onClick={() => navigate("/register")}
                  className="register-btn"
                >
                  Sign Up
                </Button>
              </>
            )}
          </div>
        </div>
      </header>
    </>
  );
}

export default Header;
