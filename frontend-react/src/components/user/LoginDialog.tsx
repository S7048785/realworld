"use client";
import { useAnimate } from "motion/react";

import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog.tsx";
import { Button } from "@/components/ui/button.tsx";
import { LoginForm } from "./LoginForm.tsx";
import { RegisterForm } from "./RegisterForm.tsx";
import { useState, useEffect, useRef } from "react";
import toast from "react-hot-toast";
import api from "@/api/user.ts";
import { z, ZodError } from "zod";
import { setToken } from "@/utils/token.ts";
import { useUserStore } from "@/store/userStore.ts";

// 登录表单 Schema
const loginSchema = z.object({
  email: z.email("请输入有效的邮箱地址"),
  password: z.string().min(1, "请输入密码").min(6, "密码长度至少6个字符"),
});

type LoginFormData = z.infer<typeof loginSchema>;

// 注册表单 Schema
const registerSchema = z
  .object({
    email: z.email("请输入有效的邮箱地址"),
    password: z.string().min(1, "请输入密码").min(6, "密码长度至少6个字符"),
    confirmPassword: z.string().min(1, "请确认密码"),
  })
  .refine((data) => data.password === data.confirmPassword, {
    message: "两次密码不一致",
    path: ["confirmPassword"],
  });

type RegisterFormData = z.infer<typeof registerSchema>;

export default function LoginDialog({
  isOpen,
  onClose,
}: {
  isOpen: boolean;
  onClose: () => void;
}) {
  const setUser = useUserStore((state) => state.setUser);

  const [scope, animate] = useAnimate();
  const [formStatus, setFormStatus] = useState<"login" | "register">("login");
  const [isLoading, setIsLoading] = useState(false);

  // 登录表单状态
  const [loginData, setLoginData] = useState<LoginFormData>({
    email: "",
    password: "",
  });
  const [loginErrors, setLoginErrors] = useState<
    Partial<Record<keyof LoginFormData, string>>
  >({});

  // 注册表单状态
  const [registerData, setRegisterData] = useState<RegisterFormData>({
    email: "",
    password: "",
    confirmPassword: "",
  });
  const [registerErrors, setRegisterErrors] = useState<
    Partial<Record<keyof RegisterFormData, string>>
  >({});

  const prevIsOpen = useRef(isOpen);

  // 关闭对话框时清空表单状态
  useEffect(() => {
    if (prevIsOpen.current && !isOpen) {
      // 从打开变为关闭
      setLoginData({ email: "", password: "" });
      setRegisterData({ email: "", password: "", confirmPassword: "" });
      setLoginErrors({});
      setRegisterErrors({});
      setFormStatus("login");
    }
    prevIsOpen.current = isOpen;
  }, [isOpen]);

  // 切换登录/注册
  const toggleFormStatus = async () => {
    await animate(scope.current, { height: 0 });
    setFormStatus((prev) => (prev === "login" ? "register" : "login"));
    setLoginErrors({});
    setRegisterErrors({});
    await animate(scope.current, { height: "auto" });
  };

  // 验证登录表单
  const validateLogin = (): boolean => {
    const result = loginSchema.safeParse(loginData);
    if (!result.success) {
      const newErrors: Partial<Record<keyof LoginFormData, string>> = {};
      const error = result.error as ZodError;
      error.issues.forEach((issue) => {
        const field = issue.path[0] as keyof LoginFormData;
        newErrors[field] = issue.message;
      });
      setLoginErrors(newErrors);
      return false;
    }
    setLoginErrors({});
    return true;
  };

  // 验证注册表单
  const validateRegister = (): boolean => {
    const result = registerSchema.safeParse(registerData);
    if (!result.success) {
      const newErrors: Partial<Record<keyof RegisterFormData, string>> = {};
      const error = result.error as ZodError;
      error.issues.forEach((issue) => {
        const field = issue.path[0] as keyof RegisterFormData;
        newErrors[field] = issue.message;
      });
      setRegisterErrors(newErrors);
      return false;
    }
    setRegisterErrors({});
    return true;
  };

  // 登录处理
  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!validateLogin()) return;

    setIsLoading(true);
    try {
      const res = await api.login(loginData);
      if (res.code === 200) {
        setToken(res.data.access_token);
        setUser(res.data.user);
        toast.success("登录成功！");
        onClose();
      } else {
        console.log(res);
        toast.error(res?.msg || "登录失败");
        setIsLoading(false);
      }
    } catch (error) {
      toast.error("登录失败，请稍后重试");
      setIsLoading(false);

    }
  };

  // 注册处理
  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!validateRegister()) return;

    setIsLoading(true);
    try {
      const res = await api.register({
        email: registerData.email,
        password: registerData.password,
      });
      if (res.code === 200) {
        toast.success("注册成功！请前往登录账号");
        setFormStatus("login");
        setRegisterData({ email: "", password: "", confirmPassword: "" });
      } else {
        setIsLoading(false);
        toast.error(res?.msg || "注册失败");
      }
    } catch {
      setIsLoading(false);
      toast.error("注册失败，请稍后重试");
    }
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle className="text-center text-2xl">
            {formStatus === "login" ? "登录到 Conduit 🌈" : "注册 Conduit 🌙"}
          </DialogTitle>
          <DialogDescription className="text-center">
            {formStatus === "login"
              ? "请在下方输入您的邮箱号和密码以登录您的账户"
              : "创建一个新账号，开始您的旅程"}
          </DialogDescription>
        </DialogHeader>
        <form>
          <div className="">
            <div ref={scope} className="overflow-y-hidden px-1">
              {formStatus === "login" ? (
                <LoginForm
                  data={loginData}
                  errors={loginErrors}
                  onChange={setLoginData}
                  isLoading={isLoading}
                />
              ) : (
                <RegisterForm
                  data={registerData}
                  errors={registerErrors}
                  onChange={setRegisterData}
                  isLoading={isLoading}
                />
              )}
            </div>
            <div className="flex flex-col gap-3">
              <Button
                onClick={formStatus === "login" ? handleLogin : handleRegister}
                type="submit"
                className="w-full"
                disabled={isLoading}
              >
                {isLoading ? "加载中..." : formStatus === "login" ? "登录" : "注册"}
              </Button>
              <Button
                onClick={toggleFormStatus}
                type="button"
                variant="outline"
                className="w-full"
                disabled={isLoading}
              >
                {formStatus === "login"
                  ? "没有账号? 点击注册"
                  : "已有账号? 点击登录"}
              </Button>
            </div>
          </div>
        </form>
        <DialogFooter></DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
