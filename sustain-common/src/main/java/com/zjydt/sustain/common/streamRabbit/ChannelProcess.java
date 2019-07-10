package com.zjydt.sustain.common.streamRabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ChannelProcess {
//    public static final String USER_OUTPUT = "user_output"; // 输出通道名称
//    public static final String USER_INPUT = "user_input"; // 输入通道名称
//
//    public static final String TEACHER_OUTPUT = "teacher_output"; // 输出通道名称
//    public static final String TEACHER_INPUT = "teacher_input"; // 输入通道名称

    public static final String PERCEPT_OUTPUT = "percept_output"; // 输出通道名称
    public static final String PERCEPT_INPUT = "percept_input"; // 输入通道名称

//    @Input(ChannelProcess.USER_INPUT)
//    public SubscribableChannel userInput();
//
//    @Input(ChannelProcess.TEACHER_INPUT)
//    public SubscribableChannel teacherInput();

    @Input(ChannelProcess.PERCEPT_INPUT)
    public SubscribableChannel perceptInput();

//    @Output(ChannelProcess.USER_OUTPUT)
//    public MessageChannel userOutput();
//
//    @Output(ChannelProcess.TEACHER_OUTPUT)
//    public MessageChannel teacherOutput();

    @Output(ChannelProcess.PERCEPT_OUTPUT)
    public MessageChannel perceptOutput();
}
