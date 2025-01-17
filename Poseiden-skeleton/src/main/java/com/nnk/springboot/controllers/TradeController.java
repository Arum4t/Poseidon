package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
/**
 * CRUD for trade
 *
 * @author Quentin
 */
@Controller
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @RequestMapping("/trade/list")
    public String getTradeList(Model model)
    {
        model.addAttribute("trade", tradeRepository.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validateTrade(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeRepository.save(trade);
        model.addAttribute("message", "Trade add successfully !");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateTradeForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeRepository.save(trade);
        model.addAttribute("trade", tradeRepository.findAll());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        model.addAttribute("trade", tradeRepository.findAll());
        return "redirect:/trade/list";
    }
}
