//package com.tianchang;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//
//@ServletComponentScan // 添加这个注解
//@SpringBootApplication
//public class AppApplication {
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(AppApplication.class, args);
//
//    }
//
//}

package com.tianchang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AppApplication {

    // 虚空色系
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String DEEP_SPACE = "\u001B[38;5;17m";
    private static final String QUANTUM_BLUE = "\u001B[38;5;27m";
    private static final String EVENT_HORIZON = "\u001B[38;5;90m";
    private static final String MATRIX_GREEN = "\u001B[38;5;40m";
    private static final String DARK_ENERGY = "\u001B[38;5;53m";
    private static final String SINGULARITY = "\u001B[38;5;15m";

    private static void printBanner() {
        System.out.println(DARK_ENERGY + "\n▃▃▃▃▃▃▃▃▃▃▃▃ 量子观测协议激活 ▃▃▃▃▃▃▃▃▃▃▃▃" + ANSI_RESET);

        String[] voidEarth = {
                DEEP_SPACE + "         ╔═╗╔═╦═══╗      " + QUANTUM_BLUE + " ░▒▓███████",
                DEEP_SPACE + "         ║ ║║ ║╔═╗║      " + QUANTUM_BLUE + "  星体演算中",
                DEEP_SPACE + "         ║ ║║ ║║ ║╠══╦╗ " + EVENT_HORIZON + "◖⊙◗ 轨道精度±0.0003″",
                DEEP_SPACE + "         ║ ║║ ║║ ║║══╣║ " + MATRIX_GREEN + "◈◈◈ 生态模拟器在线",
                DEEP_SPACE + "         ╚═╝╚═╩╩═╝╩══╩╝ " + SINGULARITY + "v̸o̸i̸d̸-̸e̸a̸r̸t̸h̸.̸1̸.̸0̸"
        };

        // 三维地球绘制
        String[] earthRender = {
                "  " + QUANTUM_BLUE + "    ▗▄▄▄▄▄▄▄▄▄▖    ",
                " " + QUANTUM_BLUE + " ▄█████████████████▄ ",
                QUANTUM_BLUE + "▐" + MATRIX_GREEN + "■■■■" + QUANTUM_BLUE + "████" + DARK_ENERGY + "◌" + QUANTUM_BLUE + "████" + MATRIX_GREEN + "■■■■" + QUANTUM_BLUE + "▌",
                QUANTUM_BLUE + "█" + MATRIX_GREEN + "■■■■■■" + QUANTUM_BLUE + "███" + DARK_ENERGY + "◍" + QUANTUM_BLUE + "███" + MATRIX_GREEN + "■■■■■■" + QUANTUM_BLUE + "█",
                QUANTUM_BLUE + "█" + MATRIX_GREEN + "■■■■■■■" + QUANTUM_BLUE + "██" + DARK_ENERGY + "◎" + QUANTUM_BLUE + "██" + MATRIX_GREEN + "■■■■■■■" + QUANTUM_BLUE + "█",
                " " + QUANTUM_BLUE + " ▀█████████████████▀ ",
                "  " + QUANTUM_BLUE + "    ▝▀▀▀▀▀▀▀▀▀▘    "
        };

        for (String line : voidEarth) {
            System.out.println(line);
        }
        System.out.println(DARK_ENERGY + "════════════════ 现实锚点锁定 ════════════════" + ANSI_RESET);

        // 动态虚空效果
        System.out.println(SINGULARITY + "       ░▒▓ 存在性证明协议：");
        System.out.println(EVENT_HORIZON + "       [Schrödinger-√]  " + MATRIX_GREEN + "量子态叠加确认");
        System.out.println(QUANTUM_BLUE + "       [Einstein-!]    " + DARK_ENERGY + "时空曲率异常");

        // 禅意标语
        System.out.println(SINGULARITY + "\n     存在即感知 | 虚空生万物");
        System.out.println(DARK_ENERGY + "══════════════════════════════════════════" + ANSI_RESET);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
        printBanner();
        System.out.println(QUANTUM_BLUE + "[系统就绪]" + SINGULARITY + " 量子地址：void://earth.0x7F");
        System.out.println(EVENT_HORIZON + "警告：本系统观测将影响现实稳定性");
        System.out.println(EVENT_HORIZON + "🌍 启动成功" + ANSI_RESET);
    }
}