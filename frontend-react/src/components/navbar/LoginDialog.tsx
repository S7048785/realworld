"use client";
import { useAnimate } from "motion/react";

import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { type RefObject, useEffect, useRef, useState } from "react";
// import { validateUserForm } from "@/lib/validata";
// import toast from "react-hot-toast";
// import api from "@/lib/request";

export default function LoginDialog({
  isOpen,
  onClose,
}: {
  isOpen: boolean;
  onClose: () => void;
}) {
  const emailInputRef = useRef<HTMLInputElement>(null);
  const passwordInputRef = useRef<HTMLInputElement>(null);

  const btnRef = useRef<HTMLButtonElement>(null);
  const login = async (e: React.FormEvent) => {
    // e.preventDefault();
    // btnRef.current!.disabled = true;
    //
    // // 校验表单
    // if (
    //   !validateUserForm(
    //     {
    //       username: emailInputRef.current!.value,
    //       password: passwordInputRef.current!.value,
    //     },
    //     (message) => {
    //       toast.error(message);
    //     }
    //   )
    // ) {
    //   btnRef.current!.disabled = false;
    //   return;
    // }
    //
    // // 发送登录请求
    // const res = (
    //   await api.loginUser(
    //     emailInputRef.current!.value,
    //     passwordInputRef.current!.value
    //   )
    // ).data;
    // console.log(res.data);
    // if (res.code === 200) {
    //   toast.success("登录成功！");
    //   window.location.reload();
    // } else {
    //   toast.error(res?.msg || "登录失败");
    //   btnRef.current!.disabled = false;
    // }
  };

  const register = async (e: React.FormEvent) => {
    // e.preventDefault();
    // btnRef.current!.disabled = true;
    // if (passwordInputRef.current!.value !== passwordInputRef.current!.value) {
    //   toast.error("两次密码不一致");
    //   btnRef.current!.disabled = false;
    //   return;
    // }
    //
    // if (
    //   !validateUserForm(
    //     {
    //       username: emailInputRef.current!.value,
    //       password: passwordInputRef.current!.value,
    //     },
    //     (message) => {
    //       toast.error(message);
    //     }
    //   )
    // ) {
    //   btnRef.current!.disabled = false;
    //   return;
    // }
    // const res = (
    //   await api.registerUser({
    //     username: emailInputRef.current!.value,
    //     userpassword: passwordInputRef.current!.value,
    //   })
    // ).data;
    // console.log(res.data);
    // if (res.code !== 200) {
    //   toast.error(res?.msg || "注册失败");
    //   return;
    // }
    // toast.success("注册成功！请前往登录账号");
  };

  const [scope, animate] = useAnimate();
  const [formStatus, setFormStatus] = useState(0);

  useEffect(() => {
    if (scope.current) {
      animate(
        scope.current,
        { height: "auto" },
        { type: "spring", duration: 0.5 }
      );
    }
  }, [formStatus]);
  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle className="text-center text-2xl">
            {formStatus === 0 ? "登录到 Conduit 🌈" : "注册 Conduit 🌙"}
          </DialogTitle>
          <DialogDescription className="text-center">
            {formStatus === 0
              ? "请在下方输入您的邮箱号和密码以登录您的账户"
              : "创建一个新账号，开始您的旅程"}
          </DialogDescription>
        </DialogHeader>
        <form>
          <div className="">
            <div ref={scope} className="overflow-y-hidden">
              {formStatus === 0 ? (
                <LoginForm
                  emailInputRef={emailInputRef}
                  passwordInputRef={passwordInputRef}
                />
              ) : (
                <RegisterForm
                  emailInputRef={emailInputRef}
                  passwordInputRef={passwordInputRef}
                />
              )}
            </div>
            <div className="flex flex-col gap-3">
              <Button
                ref={btnRef}
                onClick={formStatus === 0 ? login : register}
                type="submit"
                className="w-full"
              >
                {formStatus === 0 ? "登录" : "注册"}
              </Button>
              <Button
                onClick={() => {
                  animate(scope.current, { height: 0 });
                  setTimeout(() => {
                    setFormStatus(formStatus === 0 ? 1 : 0);
                  }, 500);
                }}
                type="button"
                variant="outline"
                className="w-full"
              >
                没有账号? 点击注册
              </Button>
            </div>
          </div>
        </form>
        <DialogFooter></DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

function LoginForm({
  emailInputRef,
  passwordInputRef,
}: {
  emailInputRef: RefObject<HTMLInputElement | null>;
  passwordInputRef: RefObject<HTMLInputElement | null>;
}) {
  return (
    <>
      <div className="flex flex-col gap-6 mb-8">
        <div className="grid gap-1 px-1">
          <label htmlFor="email">邮箱</label>
          <Input
            ref={emailInputRef}
            id="email"
            type="email"
            placeholder="Enter Your Email"
            required
          />
        </div>
        <div className="grid gap-1 px-1">
          <div className="flex items-center">
            <label htmlFor="password">密码</label>
            <a
              href="#"
              className="ml-auto inline-block text-sm underline-offset-4 hover:underline"
            >
              忘记了你的密码?
            </a>
          </div>
          <Input
            ref={passwordInputRef}
            id="password"
            type="password"
            required
            autoComplete="on"
          />
        </div>
      </div>
    </>
  );
}

function RegisterForm({
  emailInputRef,
  passwordInputRef,
}: {
  emailInputRef: RefObject<HTMLInputElement | null>;
  passwordInputRef: RefObject<HTMLInputElement | null>;
}) {
  return (
    <>
      <div className="flex flex-col gap-6 mb-8">
        <div className="grid gap-1 px-1">
          <label htmlFor="email">邮箱</label>
          <Input
            ref={emailInputRef}
            id="email"
            type="email"
            placeholder="Your Email"
            required
          />
        </div>
        <div className="grid gap-1 px-1">
          <div className="flex items-center">
            <label htmlFor="password">密码</label>
          </div>
          <Input
            ref={passwordInputRef}
            id="password"
            type="password"
            required
            autoComplete="on"
            placeholder="******"
          />
        </div>
        <div className="grid gap-1 px-1">
          <div className="flex items-center">
            <label htmlFor="password">确认密码</label>
          </div>
          <Input
            id="confirmPassword"
            type="password"
            required
            autoComplete="on"
            placeholder="******"
          />
        </div>
      </div>
    </>
  );
}
